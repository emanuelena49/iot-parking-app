package uniud.iot.lab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import uniud.iot.lab.dataProvider.DistancesProvider;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestObserverObservablePattern {

    class DistancesClient implements Observer {

        private DistancesProvider distancesProvider;
        private int nUpdates;
        private Map<String, Integer> lastDistaces;

        public DistancesClient(DistancesProvider provider) {
            this.distancesProvider = provider;
            this.nUpdates = 0;
            this.lastDistaces = null;
        }

        @Override
        public void update(Observable o, Object arg) {

            nUpdates++;

            lastDistaces = distancesProvider.getDistances();
        }

        public Map<String, Integer> getLastDistaces() {
            return this.lastDistaces;
        }

        public int getnUpdates() {
            return nUpdates;
        }
    }

    private DistancesProvider provider;
    private DistancesClient client1;

    @Before
    public void setUp() {
        provider = new DistancesProvider();
        client1 = new DistancesClient(provider);
        provider.addObserver(client1);
    }

    @After
    public void tearDown() {
        provider = null;
        client1 = null;
    }

    @Test
    public void testNoUpdates() {
        assertEquals(0, client1.getnUpdates());
    }

    @Test
    public void testEmptyDistances() {
        assertEquals(null, client1.getLastDistaces());
    }

    @Test
    public void testOneUpdate() {

        Map distaces = new HashMap<String, Integer>();

        distaces.put("right", 150);
        distaces.put("center", 180);
        distaces.put("left", 250);

        // set a value (and notify all observers)
        provider.setDistances(distaces);

        assertEquals(1, client1.getnUpdates());
        assertEquals(distaces, client1.getLastDistaces());
    }
}