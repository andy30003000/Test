import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataProviderImpl implements DataProvider {
    public Map<Key, List<Listener>> getListenerMap() {
        return listenerMap;
    }

    public void setListenerMap(Map<Key, List<Listener>> listenerMap) {
        this.listenerMap = listenerMap;
    }

    Map<Key,List<Listener>> listenerMap = new ConcurrentHashMap<>();

    @Override
    public void register(Key key, Listener listener) {
        List<Listener> listenersList = this.getListenerMap().get(key);
        if(listenersList == null){
            listenersList = new CopyOnWriteArrayList<>();
        }
        if(listenersList.contains(listener)){
            //do nothing or report error?
        }else{
            listenersList.add(listener);
        }

    }

    @Override
    public void unregister(Key key, Listener listener) {
        List<Listener> listenersList = this.getListenerMap().get(key);
        if(listenersList == null){
            return;
        }
        listenersList.remove(listener);
        if(listenersList.size() == 0){
            this.getListenerMap().remove(key);
        }

    }

    @Override
    public void update(Key key, Value value) {
        List<Listener> listenersList = this.getListenerMap().get(key);
        if(listenersList == null){
            //do nothing
        }else{
            listenersList.forEach(listener ->listener.handleUpdate(value));
        }

    }
}
