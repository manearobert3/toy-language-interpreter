package Controller;

import ADT.MyIHeap;
import Stmts.IStmt;
import ADT.MyIStack;
import ADT.PrgState;
import Repository.IRepository;
import Values.RefValue;
import Values.Value;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Controller {
    private IRepository repo;
    private ExecutorService executor;
    private boolean flag;
    public List<PrgState> getPrgList() {
        return repo.getPrgList();
    }

    public Controller(IRepository repo, boolean flag) {
        this.repo = repo;
        this.flag = flag;
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

//    public PrgState oneStep(PrgState state) throws ToyLanguageException {
//        MyIStack<IStmt> stk=state.getExeStack();
//        if(stk.isEmpty()) throw new ToyLanguageException("prgstate stack is empty");
//        IStmt crtStmt = stk.pop();
//        return crtStmt.execute(state);
//    }

//        public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddresses, List<Integer> heapAddresses, Map<Integer, Value> heap) {
//            return heap.entrySet().stream()
//                    .filter(e -> ( symTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())))
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        }

    public List<Integer> getAddressesFromSymTableOrHeap(Collection<Value> symTableValuesOrHeap) {
        return symTableValuesOrHeap.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAdress();
                })
                .collect(Collectors.toList());
    }


    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddresses, List<Integer> heapAddresses, Map<Integer, Value> heap) {
        Set<Integer> reachableAddresses = new HashSet<>();

        for (int address : symTableAddresses) {
            exploreAddress(address, heap, reachableAddresses);
        }

        for (int address : heapAddresses) {
            exploreAddress(address, heap, reachableAddresses);
        }

        return heap.entrySet().stream()
                .filter(entry -> reachableAddresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void exploreAddress(int address, Map<Integer, Value> heap, Set<Integer> reachableAddresses) {
        if (reachableAddresses.contains(address)) {
            return; // Already explored
        }

        reachableAddresses.add(address);

        Value value = heap.get(address);
        if (value instanceof RefValue) {
            int referencedAddress = ((RefValue) value).getAdress();
            exploreAddress(referencedAddress, heap, reachableAddresses);
        }
    }
    public void conservativeGarbageCollector(List<PrgState> programStateList) {
        // Collect addresses from all symbol tables and the shared heap
        List<Integer> symTableAddresses = programStateList.stream()
                .flatMap(p -> getAddressesFromSymTableOrHeap(p.getSymTable().getMap().values()).stream())
                .collect(Collectors.toList());

        List<Integer> heapAddresses = getAddressesFromSymTableOrHeap(programStateList.get(0).getHeap().getContent().values());

        // Explore addresses
        Set<Integer> reachableAddresses = new HashSet<>();
        symTableAddresses.forEach(address -> exploreAddress(address, programStateList.get(0).getHeap().getContent(), reachableAddresses));
        heapAddresses.forEach(address -> exploreAddress(address, programStateList.get(0).getHeap().getContent(), reachableAddresses));

        // Filter heap entries based on reachable addresses
        Map<Integer, Value> newHeapContent = programStateList.get(0).getHeap().getContent().entrySet().stream()
                .filter(entry -> reachableAddresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Update the heap content for all program states
        programStateList.get(0).getHeap().setContent(newHeapContent);

    }

    public void setFlagOpt(boolean value) {
        this.flag = value;
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException,ToyLanguageException {
        // Before execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException | ToyLanguageException e) {
                e.getMessage();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                        return p.oneStep();
                }))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;

                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        conservativeGarbageCollector(prgList);
        prgList.forEach(p -> {
            try {
                repo.logPrgStateExec(p);
            } catch (ToyLanguageException | IOException e) {

                e.printStackTrace();
            }
        });

        // Save the current programs in the repository
        repo.setPrgList(prgList);
        if (this.flag) {
            prgList.forEach(p -> {
                System.out.println(p.toString());
            });
        }

    }
    public void oneStep() throws ToyLanguageException, IOException, InterruptedException{
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        //List<PrgState> prgList=repo.getPrgList();


        if (prgList.isEmpty()) {
//            repo.setPrgList(prgList);
//
//            executor.shutdownNow();

            return;
        }
        if(prgList.size()>0){

            oneStepForAllPrg(prgList);

            prgList=removeCompletedPrg(repo.getPrgList());
        }

    }
    public void allStep() throws ToyLanguageException, IOException, InterruptedException{
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            oneStepForAllPrg(prgList);
           // conservativeGarbageCollector(prgList);
        prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(prgList);
    }
}


