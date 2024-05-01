package com.example.art.component;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.service.StartProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {
    private final StartProgramService startProgramService;
    private static final String[] PRODUCT_NAMES = {"Электродвигатель", "Двигатель внутреннего сгорания", "Паровой двигатель"};
    private static final String[] ASSEMBLY_UNIT_NAMES = {"Статор", "Ротор", "Корпус", "Болт", "Гильза прямоугольная"};
    private static final String[] PART_NAMES = {"Корпус статора", "Гильза прямоугольная", "Крышка ротора", "Втулка", "Болт"};
    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }


    private void loadInitialData() {
        try {
            Random random = new Random();

            for (int i = 0; i < 100; i++) { // TODO: 10 млн
                Product product = createProduct(random);
                startProgramService.saveProduct(product);

                for (int j = 0; j < random.nextInt(91) + 10; j++) {
                    AssemblyUnit assemblyUnit = createAssemblyUnit(product, random);
                    startProgramService.saveAssemblyUnit(assemblyUnit);

                    for (int k = 0; k < random.nextInt(91) + 10; k++) {
                        Part part = createPart(assemblyUnit, random);
                        startProgramService.savePart(part);
                    }


                }


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Product createProduct(Random random) {
        Product product = new Product();
        product.setDesignation("ИБПШ." + (random.nextInt(1000000) + 100000) + ".00" + random.nextInt(10));
        product.setName(PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)]);
        product.setQuantity(random.nextInt(11));
        product.setLevel(1);
        product.setVersionDate(random.nextInt(75) + 1950);
        return product;
    }

    private AssemblyUnit createAssemblyUnit(Product product, Random random) {
        AssemblyUnit assemblyUnit = new AssemblyUnit();
        assemblyUnit.setDesignation("ЖБИК." + (random.nextInt(1000000) + 100000) + ".00" + random.nextInt(10));
        assemblyUnit.setName(ASSEMBLY_UNIT_NAMES[random.nextInt(ASSEMBLY_UNIT_NAMES.length)]);
        assemblyUnit.setQuantity(random.nextInt(301));
        assemblyUnit.setLevel(2);
        assemblyUnit.setVersionDate(random.nextInt(75) + 1950);
        assemblyUnit.setProduct(product);
        return assemblyUnit;
    }

    private Part createPart(AssemblyUnit assemblyUnit, Random random) {
        Part part = new Part();
        part.setDesignation("ЖБИК." + (random.nextInt(1000000) + 100000) + ".00" + random.nextInt(10));
        part.setName(PART_NAMES[random.nextInt(PART_NAMES.length)]);
        part.setQuantity(random.nextInt(301));
        part.setLevel(3);
        part.setVersionDate(random.nextInt(75) + 1950);
        part.setAssemblyUnit(assemblyUnit);
        return part;
    }


//    private void loadInitialData() {
//        // Создаем изделие
//        Product product = new Product();
//        product.setDesignation("ИБПШ.526824.001");
//        product.setName("Электродвигатель");
//        product.setQuantity(1);
//        product.setLevel(1);
//
//        // Сохраняем изделие в базу данных
//        startProgramService.saveProduct(product);
//
//        // Создаем сборочные единицы
//        AssemblyUnit stator = new AssemblyUnit();
//        stator.setDesignation("ЖБИК.684222.002");
//        stator.setName("Статор");
//        stator.setQuantity(2);
//        stator.setLevel(2);
//        stator.setProduct(product);
//
//        AssemblyUnit rotor = new AssemblyUnit();
//        rotor.setDesignation("ЖБИК.684261.003");
//        rotor.setName("Ротор");
//        rotor.setQuantity(1);
//        rotor.setLevel(2);
//        rotor.setProduct(product);
//
//        AssemblyUnit housing = new AssemblyUnit();
//        housing.setDesignation("ЖБИК.301131.001");
//        housing.setName("Корпус");
//        housing.setQuantity(1);
//        housing.setLevel(2);
//        housing.setProduct(product);
//
//        AssemblyUnit bolt = new AssemblyUnit();
//        bolt.setDesignation("ЖБИК.301131.001");
//        bolt.setName("Болт");
//        bolt.setQuantity(20);
//        bolt.setLevel(2);
//        bolt.setProduct(product);
//
//        AssemblyUnit rectangularSleeve = new AssemblyUnit();
//        rectangularSleeve.setDesignation("ЖБИК.757481.001");
//        rectangularSleeve.setName("Гильза прямоугольная");
//        rectangularSleeve.setQuantity(20);
//        rectangularSleeve.setLevel(2);
//        rectangularSleeve.setProduct(product);
//
//        // Сохраняем сборочные единицы в базу данных
//        startProgramService.saveAssemblyUnit(stator);
//        startProgramService.saveAssemblyUnit(rotor);
//        startProgramService.saveAssemblyUnit(housing);
//        startProgramService.saveAssemblyUnit(bolt);
//        startProgramService.saveAssemblyUnit(rectangularSleeve);
//
//        // Создаем детали
//        Part statorHousing = new Part();
//        statorHousing.setDesignation("ЖБИК.301134.001");
//        statorHousing.setName("Корпус статора");
//        statorHousing.setQuantity(1);
//        statorHousing.setLevel(3);
//        statorHousing.setAssemblyUnit(stator);
//
//        Part statorRectangularSleeve = new Part();
//        statorRectangularSleeve.setDesignation("ЖБИК.757481.001");
//        statorRectangularSleeve.setName("Гильза прямоугольная");
//        statorRectangularSleeve.setQuantity(45);
//        statorRectangularSleeve.setLevel(3);
//        statorRectangularSleeve.setAssemblyUnit(stator);
//
//        Part rotorCover = new Part();
//        rotorCover.setDesignation("ЖБИК.301176.001");
//        rotorCover.setName("Крышка ротора");
//        rotorCover.setQuantity(1);
//        rotorCover.setLevel(3);
//        rotorCover.setAssemblyUnit(rotor);
//
//        Part rotorSleeve = new Part();
//        rotorSleeve.setDesignation("ЖБИК.714662.001");
//        rotorSleeve.setName("Втулка");
//        rotorSleeve.setQuantity(10);
//        rotorSleeve.setLevel(3);
//        rotorSleeve.setAssemblyUnit(rotor);
//
//        Part rotorBolt = new Part();
//        rotorBolt.setDesignation("ЖБИК.758121.007");
//        rotorBolt.setName("Болт");
//        rotorBolt.setQuantity(16);
//        rotorBolt.setLevel(3);
//        rotorBolt.setAssemblyUnit(rotor);
//
//        // Сохраняем детали в базу данных
//        startProgramService.savePart(statorHousing);
//        startProgramService.savePart(statorRectangularSleeve);
//        startProgramService.savePart(rotorCover);
//        startProgramService.savePart(rotorSleeve);
//        startProgramService.savePart(rotorBolt);
//
//        // Остальные детали аналогичным образом
//    }
}
