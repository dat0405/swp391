package com.tatdat.sundayshop;


import com.tatdat.sundayshop.entity.Fruit;
import jakarta.persistence.*;

import java.util.List;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.giaolang.sundayshop-PU");

    private static EntityManager em = emf.createEntityManager();


    static void main() {
        //createFruits();
        //getFruits();
        getFruitById();

    }

    public static void getFruitById(){
        Fruit fruit = em.find(Fruit.class, "abc");
        if (fruit == null){
            System.out.println("No such fruits exists");
        }
        fruit = em.find(Fruit.class, "dd");
        System.out.println("FOUND: " + fruit.toString());

    }

    public static void getFruits(){

        Query query = em.createQuery("select f from Fruit f");
        List<Fruit> fruits = query.getResultList();
        System.out.println("Total Fruits: " + fruits.size());
        for (Fruit x : fruits){ // toan tu voi moi trong toan
            System.out.println(x.toString());

        }
        //List<Fruit> fruits = em.createQuery("select f from Fruit f").getResultList();

    }

    public static void createFruits(){

        Fruit cau = new Fruit("MC", "MANG CAU", "MANG CAU LA TRAI DAU TIEN ...", 5.0);
        Fruit sung = new Fruit("SS", "SUNG SUONG", "SUNG SUONG LA TRAI ...", 6.0);
        Fruit dua = new Fruit("DD", "DZUA DZUA", "DUA DUA CX CXLA TRAI DAU ...", 7.0);

        em.getTransaction().begin();

        em.persist(cau);
        em.persist(sung);
        em.persist(dua);

        em.getTransaction().commit();
    }
}
