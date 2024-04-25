package com.example.art.component;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {
    private final ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        // Создаем изделие
        Product product = new Product();
        product.setDesignation("ИБПШ.526824.001");
        product.setName("Электродвигатель");
        product.setQuantity(1);
        product.setLevel(1);

        // Сохраняем изделие в базу данных
        productService.saveProduct(product);

        // Создаем сборочные единицы
        AssemblyUnit stator = new AssemblyUnit();
        stator.setDesignation("ЖБИК.684222.002");
        stator.setName("Статор");
        stator.setQuantity(2);
        stator.setLevel(2);
        stator.setProduct(product);

        AssemblyUnit rotor = new AssemblyUnit();
        rotor.setDesignation("ЖБИК.684261.003");
        rotor.setName("Ротор");
        rotor.setQuantity(1);
        rotor.setLevel(2);
        rotor.setProduct(product);

        AssemblyUnit housing = new AssemblyUnit();
        housing.setDesignation("ЖБИК.301131.001");
        housing.setName("Корпус");
        housing.setQuantity(1);
        housing.setLevel(2);
        housing.setProduct(product);

        // Сохраняем сборочные единицы в базу данных
        productService.saveAssemblyUnit(stator);
        productService.saveAssemblyUnit(rotor);
        productService.saveAssemblyUnit(housing);

        // Создаем детали
        Part statorHousing = new Part();
        statorHousing.setDesignation("ЖБИК.301134.001");
        statorHousing.setName("Корпус статора");
        statorHousing.setQuantity(1);
        statorHousing.setLevel(3);
        statorHousing.setAssemblyUnit(stator);

        Part statorRectangularSleeve = new Part();
        statorRectangularSleeve.setDesignation("ЖБИК.757481.001");
        statorRectangularSleeve.setName("Гильза прямоугольная");
        statorRectangularSleeve.setQuantity(45);
        statorRectangularSleeve.setLevel(3);
        statorRectangularSleeve.setAssemblyUnit(stator);

        Part rotorCover = new Part();
        rotorCover.setDesignation("ЖБИК.301176.001");
        rotorCover.setName("Крышка ротора");
        rotorCover.setQuantity(1);
        rotorCover.setLevel(3);
        rotorCover.setAssemblyUnit(rotor);

        Part rotorSleeve = new Part();
        rotorSleeve.setDesignation("ЖБИК.714662.001");
        rotorSleeve.setName("Втулка");
        rotorSleeve.setQuantity(10);
        rotorSleeve.setLevel(3);
        rotorSleeve.setAssemblyUnit(rotor);

        Part rotorBolt = new Part();
        rotorBolt.setDesignation("ЖБИК.758121.007");
        rotorBolt.setName("Болт");
        rotorBolt.setQuantity(16);
        rotorBolt.setLevel(3);
        rotorBolt.setAssemblyUnit(rotor);

        // Сохраняем детали в базу данных
        productService.savePart(statorHousing);
        productService.savePart(statorRectangularSleeve);
        productService.savePart(rotorCover);
        productService.savePart(rotorSleeve);
        productService.savePart(rotorBolt);

        // Остальные детали аналогичным образом
    }
}
