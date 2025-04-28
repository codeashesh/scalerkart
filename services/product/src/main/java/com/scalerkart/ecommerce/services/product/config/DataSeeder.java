package com.scalerkart.ecommerce.services.product.config;

import com.scalerkart.ecommerce.services.product.model.convertor.ProductConvertor;
import com.scalerkart.ecommerce.services.product.model.data.ProductGenerator;
import com.scalerkart.ecommerce.services.product.model.entity.Category;
import com.scalerkart.ecommerce.services.product.repository.CategoryRepository;
import com.scalerkart.ecommerce.services.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            insertCategories();
        } else {
            categoryRepository.deleteAll();
            insertCategories();
        }

        if (productRepository.count() == 0) {
            insertProducts();
        } else {
            productRepository.deleteAll();
            insertProducts();
        }
    }

    private void insertCategories() {
        // Root Categories
        Category women = categoryRepository.save(Category.builder().id("1").categoryTitle("Women").imageUrl("http://women.jpg").build());
        Category men = categoryRepository.save(Category.builder().id("2").categoryTitle("Men").imageUrl("http://men.jpg").build());

        // Women's Categories
        Category indianWear = categoryRepository.save(Category.builder()
                .id("3")
                .categoryTitle("Women - Indian Wear")
                .imageUrl("https://images.pexels.com/photos/19248192/pexels-photo-19248192.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .parentCategory(women)
                .build());

        Category westernWear = categoryRepository.save(Category.builder()
                .id("4")
                .categoryTitle("Women - Western Wear")
                .imageUrl("https://images.pexels.com/photos/2983464/pexels-photo-2983464.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .parentCategory(women)
                .build());

        Category accessoriesWomen = categoryRepository.save(Category.builder()
                .id("5")
                .categoryTitle("Women - Accessories")
                .imageUrl("https://images.pexels.com/photos/30949280/pexels-photo-30949280.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .parentCategory(women)
                .build());

        // Men's Categories
        Category clothingMen = categoryRepository.save(Category.builder()
                .id("6")
                .categoryTitle("Men - Clothing")
                .imageUrl("https://images.pexels.com/photos/30953651/pexels-photo-30953651.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .parentCategory(men)
                .build());

        Category accessoriesMen = categoryRepository.save(Category.builder()
                .id("7")
                .categoryTitle("Men - Accessories")
                .imageUrl("https://images.pexels.com/photos/380782/pexels-photo-380782.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .parentCategory(men)
                .build());

        // Indian Wear Subcategories
        Set<Category> indianSubcategories = Set.of(
                Category.builder().id("8").categoryTitle("Kurtas & Suits").imageUrl("http://kurtas_suits.jpg").parentCategory(indianWear).build(),
                Category.builder().id("9").categoryTitle("Kurtis & Tunics").imageUrl("http://kurtis_tunics.jpg").parentCategory(indianWear).build(),
                Category.builder().id("10").categoryTitle("Leggings, Salwars & Churidars").imageUrl("http://leggings.jpg").parentCategory(indianWear).build(),
                Category.builder().id("11").categoryTitle("Skirts & Palazzos").imageUrl("http://skirts_palazzos.jpg").parentCategory(indianWear).build(),
                Category.builder().id("12").categoryTitle("Sarees & Blouses").imageUrl("http://sarees_blouses.jpg").parentCategory(indianWear).build(),
                Category.builder().id("13").categoryTitle("Dress Material").imageUrl("http://dress_material.jpg").parentCategory(indianWear).build(),
                Category.builder().id("14").categoryTitle("Lehenga Choli").imageUrl("http://lehenga_choli.jpg").parentCategory(indianWear).build(),
                Category.builder().id("15").categoryTitle("Dupattas & Shawls").imageUrl("http://dupattas_shawls.jpg").parentCategory(indianWear).build()
        );
        categoryRepository.saveAll(indianSubcategories);

        // Western Wear Subcategories
        Set<Category> westernSubcategories = Set.of(
                Category.builder().id("16").categoryTitle("Dresses & Jumpsuits").imageUrl("http://dresses_jumpsuits.jpg").parentCategory(westernWear).build(),
                Category.builder().id("17").categoryTitle("Tops, T-Shirts & Shirts").imageUrl("http://tops_tshirts_shirts.jpg").parentCategory(westernWear).build(),
                Category.builder().id("18").categoryTitle("Jeans & Leggings").imageUrl("http://jeans_jeggings.jpg").parentCategory(westernWear).build(),
                Category.builder().id("19").categoryTitle("Trousers & Capri's").imageUrl("http://trousers_capris.jpg").parentCategory(westernWear).build(),
                Category.builder().id("20").categoryTitle("Shorts & Skirts").imageUrl("http://shorts_skirts.jpg").parentCategory(westernWear).build(),
                Category.builder().id("21").categoryTitle("Shrugs").imageUrl("http://shrugs.jpg").parentCategory(westernWear).build(),
                Category.builder().id("22").categoryTitle("Sweaters & Sweatshirts").imageUrl("http://sweaters_sweatshirts.jpg").parentCategory(westernWear).build(),
                Category.builder().id("23").categoryTitle("Jackets & Waistcoats").imageUrl("http://jackets_waistcoats.jpg").parentCategory(westernWear).build(),
                Category.builder().id("24").categoryTitle("Coats & Blazers").imageUrl("http://coats_blazers.jpg").parentCategory(westernWear).build()
        );
        categoryRepository.saveAll(westernSubcategories);

        // Women's Accessories Subcategories
        Category womenWatches = categoryRepository.save(Category.builder()
                .id("25")
                .categoryTitle("Women Watches")
                .imageUrl("http://women_watches.jpg")
                .parentCategory(accessoriesWomen)
                .build());

        Set<Category> womenAccessories = Set.of(
                Category.builder().id("26").categoryTitle("Analog").imageUrl("http://analog_watches.jpg").parentCategory(womenWatches).build(),
                Category.builder().id("27").categoryTitle("Chronograph").imageUrl("http://chronograph_watches.jpg").parentCategory(womenWatches).build(),
                Category.builder().id("28").categoryTitle("Digital").imageUrl("http://digital_watches.jpg").parentCategory(womenWatches).build(),
                Category.builder().id("29").categoryTitle("Analog & Digital").imageUrl("http://analog_digital_watches.jpg").parentCategory(womenWatches).build(),
                Category.builder().id("30").categoryTitle("Sunglasses").imageUrl("http://sunglasses.jpg").parentCategory(accessoriesWomen).build(),
                Category.builder().id("31").categoryTitle("Eye Glasses").imageUrl("http://eye_glasses.jpg").parentCategory(accessoriesWomen).build(),
                Category.builder().id("32").categoryTitle("Belt").imageUrl("http://belt.jpg").parentCategory(accessoriesWomen).build()
        );
        categoryRepository.saveAll(womenAccessories);

        // Men's Clothing Subcategories
        Set<Category> menClothing = Set.of(
                Category.builder().id("33").categoryTitle("T-Shirts").imageUrl("http://tshirts.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("34").categoryTitle("Casual Shirts").imageUrl("http://casual_shirts.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("35").categoryTitle("Formal Shirts").imageUrl("http://formal_shirts.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("36").categoryTitle("Suits").imageUrl("http://suits.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("37").categoryTitle("Jeans").imageUrl("http://jeans.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("38").categoryTitle("Casual Trousers").imageUrl("http://casual_trousers.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("39").categoryTitle("Formal Trousers").imageUrl("http://formal_trousers.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("40").categoryTitle("Shorts").imageUrl("http://shorts.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("41").categoryTitle("Track Pants").imageUrl("http://track_pants.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("42").categoryTitle("Sweaters & Sweatshirts").imageUrl("http://sweaters_sweatshirts.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("43").categoryTitle("Jackets").imageUrl("http://jackets.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("44").categoryTitle("Blazers & Coats").imageUrl("http://blazers_coats.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("45").categoryTitle("Sports & Active Wear").imageUrl("http://sports_active_wear.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("46").categoryTitle("Indian & Festive Wear").imageUrl("http://indian_festive_wear.jpg").parentCategory(clothingMen).build(),
                Category.builder().id("47").categoryTitle("Innerwear & Sleepwear").imageUrl("http://innerwear_sleepwear.jpg").parentCategory(clothingMen).build()
        );
        categoryRepository.saveAll(menClothing);

        // Men's Accessories Subcategories
        Set<Category> menAccessories = Set.of(
                Category.builder().id("48").categoryTitle("Watches & Wearables").imageUrl("http://watches_wearables.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("49").categoryTitle("Sunglasses & Frames").imageUrl("http://sunglasses_frames.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("50").categoryTitle("Bags & Backpacks").imageUrl("http://bags_backpacks.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("50").categoryTitle("Luggage & Trolleys").imageUrl("http://luggage_trolleys.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("51").categoryTitle("Personal Care & Grooming").imageUrl("http://personal_care.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("52").categoryTitle("Wallets & Belts").imageUrl("http://wallets_belts.jpg").parentCategory(accessoriesMen).build(),
                Category.builder().id("53").categoryTitle("Fashion Accessories").imageUrl("http://fashion_accessories.jpg").parentCategory(accessoriesMen).build()
        );
        categoryRepository.saveAll(menAccessories);
    }


    private void insertProducts() {
//        Category kurtasCategory = categoryRepository.findByCategoryTitle("Indian & Western Wear").orElse(null);
//        Category sareesCategory = categoryRepository.findByCategoryTitle("Sarees & Blouses").orElse(null);
//
//        if (kurtasCategory == null || sareesCategory == null) return;

//        Product kurta = Product.builder()
//                .name("Floral Print Kurta")
//                .description("Cotton printed kurta with intricate embroidery")
//                .price(39.99)
//                .discountedPrice(34.99)
//                .imageUrl("https://images.pexels.com/photos/13584944/pexels-photo-13584944.jpeg")
//                .brand("FashionWeave")
//                .material("Cotton")
//                .color("Blue")
//                .sizes(Set.of(36, 38, 40))
//                .categoryDetails(kurtasCategory)
//                .build();
//
//        Product saree = Product.builder()
//                .name("Silk Saree with Blouse")
//                .description("Pure Kanjeevaram silk saree with zari work")
//                .price(299.99)
//                .discountedPrice(249.99)
//                .imageUrl("https://images.pexels.com/photos/28943610/pexels-photo-28943610.jpeg")
//                .brand("SareeMantra")
//                .material("Silk")
//                .color("Red")
//                .sizes(Set.of(34, 36))
//                .categoryDetails(sareesCategory)
//                .build();

        productRepository.saveAll(ProductGenerator.getAllProducts().stream().map(ProductConvertor::fromDto).toList());
    }
}

