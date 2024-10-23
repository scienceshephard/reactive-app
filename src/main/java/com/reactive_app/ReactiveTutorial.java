package com.reactive_app;

import java.util.List;
import java.util.Locale;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple4;

public class ReactiveTutorial {

    private Mono<String> monoTest(){
        return Mono.just("Opeyemi");
    }
    private Flux<String> testFlux(){
        List<String> things= List.of("Melo", "Ope", "Itunu", "Darasimi");
        return Flux.fromIterable(things);
    }

    //Difference between flatMap and map is that:
    // map is used for synchronous flux
    private Flux<String> mapFlux(){
        Flux<String> flux= Flux.just("YYo", "boy", "Howfar");
        return flux.map(s -> s.toUpperCase(Locale.ROOT));
    }
    //flatMap is mostly used and can emits flux asynchronously but one a single flux at a time.
    private Flux<String> mapFlatFlux(){
        Flux<String> flux= Flux.just("YYo", "boy I am tired", "How far");
        return flux.flatMap(s -> Mono.just(s.toLowerCase(Locale.ROOT)));
    }
    private Flux<String> skipFlux(){
       Flux<String> flux= Flux.just("Java", "C++", "PHP", "Python", "JavaScript");
       return flux.skipLast(2).log(); 
    }
    //print numbers between a specific range and also a complex number
    private Flux<Integer> numbers(){
        Flux<Integer> flux= Flux.range(1, 10);
        return flux.map(s -> s.intValue());
    }

    private Flux<Tuple4<Integer, Integer, Integer, Integer>> test_Tuple_Zip(){
        Flux <Integer> flux1= Flux.range(1, 10).delayElements(Duration.ofMillis(500));
        Flux <Integer> flux2= Flux.range(101, 10).delayElements(Duration.ofMillis(500));
        Flux <Integer> flux3= Flux.range(205, 10).delayElements(Duration.ofMillis(500));
        Flux <Integer> flux4= Flux.range(-20, 10).delayElements(Duration.ofMillis(500));

        return Flux.zip(flux1, flux3, flux2, flux4);
    } 

    public static void main(String[] args) throws InterruptedException{
        ReactiveTutorial reactiveTutorial = new ReactiveTutorial();
        // reactiveTutorial.monoTest().subscribe(System.out::println);

        // reactiveTutorial.testFlux().subscribe(data -> System.out.println(data));
        // reactiveTutorial.mapFlux().subscribe(System.out::println);
        
        // reactiveTutorial.mapFlatFlux().subscribe(System.out::println);
        // reactiveTutorial.skipFlux().subscribe(System.out::println);

        // reactiveTutorial.test_Tuple_Zip().subscribe(System.out::println);
        // Thread.sleep(12000);
        reactiveTutorial.todoSomething1().subscribe(System.out::println);
        
    }

    // the do functions
    private Flux<Integer> todoSomething1(){
        Flux<Integer> flux= Flux.range(20, 10);
        return flux.doOnEach(signal -> System.out.print("Signal: "+ signal));
    }
    
}
