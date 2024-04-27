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
        product.setDesignation("ИБПШ." + random.nextInt(1000000) + ".00" + random.nextInt(10));
        product.setName(PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)]);
        product.setQuantity(random.nextInt(11));
        product.setLevel(1);
        product.setVersionDate(random.nextInt(75) + 1950);
        return product;
    }

    private AssemblyUnit createAssemblyUnit(Product product, Random random) {
        AssemblyUnit assemblyUnit = new AssemblyUnit();
        assemblyUnit.setDesignation("ЖБИК." + random.nextInt(1000000) + ".00" + random.nextInt(10));
        assemblyUnit.setName(ASSEMBLY_UNIT_NAMES[random.nextInt(ASSEMBLY_UNIT_NAMES.length)]);
        assemblyUnit.setQuantity(random.nextInt(301));
        assemblyUnit.setLevel(2);
        assemblyUnit.setVersionDate(random.nextInt(75) + 1950);
        assemblyUnit.setProduct(product);
        return assemblyUnit;
    }

    private Part createPart(AssemblyUnit assemblyUnit, Random random) {
        Part part = new Part();
        part.setDesignation("ЖБИК." + random.nextInt(1000000) + ".00" + random.nextInt(10));
        part.setName(PART_NAMES[random.nextInt(PART_NAMES.length)]);
        part.setQuantity(random.nextInt(301));
        part.setLevel(3);
        part.setVersionDate(random.nextInt(75) + 1950);
        part.setAssemblyUnit(assemblyUnit);
        return part;
    }
}
