package test;

import services.OS;
import services.Cache.CacheHash;

public class CacheTest {
    public static void main(String[] args) {
        System.out.println("Teste de cache");

        CacheHash cache = new CacheHash();

        for (int i = 0; i < 20; i++) {
            OS os = new OS(i, "OS" + i, "OS" + i);
            cache.add(os);
        }

        for (Integer i = 0; i < 20; i++) {
            OS os = new OS(i, "OS" + i, "OS" + i);
            System.out.println("Buscando " + os);
            System.out.println(cache.get(os.code));
        }
    }
}
