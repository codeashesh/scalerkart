package com.scalerkart.ecommerce.services.product.model.data;

import com.scalerkart.ecommerce.services.product.model.dto.CategoryDto;
import com.scalerkart.ecommerce.services.product.model.dto.DimensionDto;
import com.scalerkart.ecommerce.services.product.model.dto.ProductDto;
import com.scalerkart.ecommerce.services.product.model.dto.RatingDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public interface ProductGenerator {

    static List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();

        // Women's Clothing
        products.add(createProduct(
                null,
                "Floral Print Kurta",
                "Cotton printed kurta with intricate embroidery",
                539.99,
                434.99,
                "FashionWeave",
                "https://images.pexels.com/photos/13584944/pexels-photo-13584944.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "Blue",
                Set.of(36, 38, 40),
                new DimensionDto(28.0, 20.0, 2.0, "inches"),
                null,
                createCategory(
                        "8",
                        "Kurtas & Suits",
                        "http://kurtas_suits.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Silk Saree with Blouse",
                "Pure Kanjeevaram silk saree with zari work",
                3299.99,
                2249.99,
                "SareeMantra",
                "https://images.pexels.com/photos/28943610/pexels-photo-28943610.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Silk",
                "Red",
                Set.of(34, 36),
                new DimensionDto(32.0, 24.0, 1.5, "inches"),
                null,
                createCategory(
                        "12",
                        "Sarees & Blouses",
                        "http://sarees_blouses.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Elegant Floral Kurta",
                "Beautiful floral-printed cotton kurta with intricate embroidery.",
                1645.99,
                1539.99,
                "FabIndia",
                "https://images.pexels.com/photos/19589909/pexels-photo-19589909.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "Blue",
                Set.of(36, 38, 40),
                new DimensionDto(28.0, 20.0, 2.0, "inches"),
                Set.of("Kurta", "Ethnic", "Traditional", "Trending"),
                createCategory(
                        "8",
                        "Kurtas & Suits",
                        "http://kurtas_suits.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Simple Kurta",
                "Beautiful floral-printed cotton kurta with intricate embroidery.",
                2645.99,
                2539.99,
                "FabIndia",
                "https://images.pexels.com/photos/28213774/pexels-photo-28213774.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "Yellow",
                Set.of(36, 38, 40),
                new DimensionDto(28.0, 20.0, 2.0, "inches"),
                Set.of("Kurta", "Ethnic", "Traditional", "Best Seller"),
                createCategory(
                        "8",
                        "Kurtas & Suits",
                        "http://kurtas_suits.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Bridal Red Saree",
                "Stunning bridal saree with zari work and gold embroidery.",
                6299.99,
                4259.99,
                "SareeMantra",
                "https://images.pexels.com/photos/12747886/pexels-photo-12747886.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Silk",
                "Red",
                Set.of(36, 38),
                new DimensionDto(32.0, 24.0, 1.5, "inches"),
                Set.of("Saree", "Bridal", "Festive", "New Arrivals"),
                createCategory(
                        "12",
                        "Sarees & Blouses",
                        "http://sarees_blouses.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Simple Off White Saree",
                "Stunning Off White Saree with red border and red blouse.",
                3299.99,
                2259.99,
                "SareeMantra",
                "https://images.pexels.com/photos/29538465/pexels-photo-29538465.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "White",
                Set.of(36, 38),
                new DimensionDto(32.0, 24.0, 1.5, "inches"),
                Set.of("Saree", "Bridal", "Festive"),
                createCategory(
                        "12",
                        "Sarees & Blouses",
                        "http://sarees_blouses.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Designer Lehenga Choli",
                "Beautiful designer lehenga choli for festive occasions.",
                10349.99,
                8299.99,
                "EthnicWear",
                "https://images.pexels.com/photos/15179843/pexels-photo-15179843.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Silk",
                "Pink",
                Set.of(38, 40, 42),
                new DimensionDto(35.0, 22.0, 1.5, "inches"),
                Set.of("Lehenga", "Bridal", "Traditional", "Trending"),
                createCategory(
                        "14",
                        "Lehenga Choli",
                        "http://lehenga_choli.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Simple Lehenga Choli",
                "Beautiful lehenga choli for festive occasions.",
                3349.99,
                2299.99,
                "EthnicWear",
                "https://images.pexels.com/photos/1139450/pexels-photo-1139450.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "Red",
                Set.of(38, 40, 42),
                new DimensionDto(35.0, 22.0, 1.5, "inches"),
                Set.of("Lehenga", "Traditional"),
                createCategory(
                        "14",
                        "Lehenga Choli",
                        "http://lehenga_choli.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Simple Printed Shawl",
                "Beautiful Simple Printed Shawl for winters.",
                1349.99,
                1299.99,
                "FabIndia",
                "https://images.pexels.com/photos/4972922/pexels-photo-4972922.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Woollen",
                "Red",
                Set.of(38, 40, 42),
                new DimensionDto(35.0, 22.0, 1.5, "inches"),
                Set.of("Shawl", "Traditional"),
                createCategory(
                        "15",
                        "Dupattas & Shawls",
                        "http://dupattas_shawls.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Simple Printed Duppatta",
                "Beautiful Simple Printed Duppatta for suits.",
                1349.99,
                1299.99,
                "FabIndia",
                "https://images.pexels.com/photos/20593511/pexels-photo-20593511.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "White",
                Set.of(38, 40, 42),
                new DimensionDto(35.0, 22.0, 1.5, "inches"),
                Set.of("Duppatta", "Traditional", "Trending"),
                createCategory(
                        "15",
                        "Dupattas & Shawls",
                        "http://dupattas_shawls.jpg",
                        createCategory(
                                "3",
                                "Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        // Women's Clothing - Western Wear
        products.add(createProduct(
                null,
                "Women’s Summer Beach Dress",
                "Lightweight and breathable summer dress perfect for vacations.",
                3279.99,
                2169.99,
                "Zara",
                "https://images.pexels.com/photos/30248245/pexels-photo-30248245.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Yellow",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Summer", "Best Seller"),
                createCategory(
                        "16",
                        "Dresses & Jumpsuits",
                        "http://lehenga_choli.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Jumpsuits",
                "Lightweight and breathable Jumpsuits perfect for vacations.",
                5279.99,
                3169.99,
                "Zara",
                "https://images.pexels.com/photos/13219630/pexels-photo-13219630.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Grey",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits", "Best Seller"),
                createCategory(
                        "16",
                        "Dresses & Jumpsuits",
                        "http://lehenga_choli.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Chic Women’s Blazer",
                "Stylish double-breasted blazer for formal wear.",
                9999.99,
                8889.99,
                "H&M",
                "https://images.pexels.com/photos/7202777/pexels-photo-7202777.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Wool Blend",
                "Black",
                Set.of(36, 38, 40),
                new DimensionDto(32.0, 22.0, 1.8, "inches"),
                Set.of("Blazer", "Formal", "Work Wear", "Best Seller"),
                createCategory(
                        "24",
                        "Coats & Blazers",
                        "http://coats_blazers.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Chic Women’s Simple Blazer",
                "Stylish double-breasted simple white blazer for formal wear.",
                5999.99,
                2889.99,
                "H&M",
                "https://images.pexels.com/photos/20029553/pexels-photo-20029553.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Wool Blend",
                "White",
                Set.of(36, 38, 40),
                new DimensionDto(32.0, 22.0, 1.8, "inches"),
                Set.of("Blazer", "Formal", "Work Wear"),
                createCategory(
                        "24",
                        "Coats & Blazers",
                        "http://coats_blazers.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Chic Women’s Dress",
                "Stylish double-breasted one piece dress for formal wear.",
                9999.99,
                8889.99,
                "H&M",
                "https://images.pexels.com/photos/19621014/pexels-photo-19621014.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Wool Blend",
                "White",
                Set.of(36, 38, 40),
                new DimensionDto(32.0, 22.0, 1.8, "inches"),
                Set.of("Dress", "Formal", "Work Wear"),
                createCategory(
                        "16",
                        "Dresses & Jumpsuits",
                        "http://lehenga_choli.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Tea shirts",
                "Stylish double-breasted Tea shirts for formal wear.",
                999.99,
                889.99,
                "H&M",
                "https://images.pexels.com/photos/5181697/pexels-photo-5181697.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Wool Blend",
                "Black",
                Set.of(36, 38, 40),
                new DimensionDto(32.0, 22.0, 1.8, "inches"),
                Set.of("Tea shirts", "Formal", "Work Wear"),
                createCategory(
                        "17",
                        "Tops, T-Shirts & Shirts",
                        "http://tops_tshirts_shirts.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Short Tea shirts",
                "Stylish double-breasted Short tea shirts.",
                999.99,
                889.99,
                "H&M",
                "https://images.pexels.com/photos/16287340/pexels-photo-16287340.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Silk",
                "Golden",
                Set.of(36, 38, 40),
                new DimensionDto(32.0, 22.0, 1.8, "inches"),
                Set.of("Tea shirts", "Casual", "Party Wear", "Trending"),
                createCategory(
                        "17",
                        "Tops, T-Shirts & Shirts",
                        "http://tops_tshirts_shirts.jpg",
                        createCategory(
                                "4",
                                "Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        // Men's Clothing
        products.add(createProduct(
                null,
                "Formal Slim Fit Shirt",
                "Premium cotton formal shirt",
                2159.99,
                1559.99,
                "VanHeusen",
                "https://images.pexels.com/photos/20574049/pexels-photo-20574049.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "White",
                Set.of(38, 40, 42, 44),
                new DimensionDto(30.0, 22.0, 1.0, "inches"),
                null,
                createCategory(
                        "35",
                        "Formal Shirts",
                        "http://formal_shirts.jpg",
                        createCategory(
                                "6",
                                "Men's Clothing",
                                "http://men_clothing.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Slim Fit Jeans",
                "Stretch denim jeans with modern fit",
                2579.99,
                2069.99,
                "Levi's",
                "https://images.pexels.com/photos/4505790/pexels-photo-4505790.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Denim",
                "Blue",
                Set.of(28, 30, 32, 34),
                new DimensionDto(40.0, 14.0, 2.0, "inches"),
                null,
                createCategory(
                        "37",
                        "Jeans",
                        "http://jeans.jpg",
                        createCategory(
                                "6",
                                "Men's Clothing",
                                "http://men_clothing.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        // Accessories
        products.add(createProduct(
                null,
                "Chronograph Watch",
                "Stainless steel analog chronograph watch",
                3199.99,
                2199.99,
                "Fossil",
                "https://images.pexels.com/photos/28383342/pexels-photo-28383342.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Stainless Steel",
                "Silver",
                Set.of(18, 20),
                new DimensionDto(8.0, 6.0, 1.5, "cm"),
                null,
                createCategory(
                        "48",
                        "Watches & Wearables",
                        "http://watches_wearables.jpg",
                        createCategory(
                                "7",
                                "Accessories",
                                "http://men_accessories.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));
        products.add(createProduct(
                null,
                "Slim Fit Formal Shirt",
                "Premium cotton formal shirt for office and special occasions.",
                2559.99,
                2059.99,
                "VanHeusen",
                "https://images.pexels.com/photos/18675901/pexels-photo-18675901.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "White",
                Set.of(38, 40, 42),
                new DimensionDto(30.0, 22.0, 1.0, "inches"),
                Set.of("Shirt", "Office", "Formal", "Best Seller"),
                createCategory(
                        "35",
                        "Formal Shirts",
                        "http://formal_shirts.jpg",
                        createCategory(
                                "6",
                                "Men's Clothing",
                                "http://men_clothing.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Men’s Casual Trousers",
                "Comfortable and stylish trousers for daily wear.",
                2549.99,
                2139.99,
                "Levi's",
                "https://images.pexels.com/photos/2453823/pexels-photo-2453823.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton Blend",
                "Navy Blue",
                Set.of(30, 32, 34),
                new DimensionDto(40.0, 14.0, 2.0, "inches"),
                Set.of("Trousers", "Casual", "Daily Wear"),
                createCategory(
                        "38",
                        "Casual Trousers",
                        "http://casual_trousers.jpg",
                        createCategory(
                                "6",
                                "Men's Clothing",
                                "http://men_clothing.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        // Accessories
        products.add(createProduct(
                null,
                "Chronograph Watch",
                "Stainless steel analog chronograph watch with waterproof design.",
                9199.99,
                8199.99,
                "Fossil",
                "https://images.pexels.com/photos/3651834/pexels-photo-3651834.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Stainless Steel",
                "Silver", Set.of(18, 20),
                new DimensionDto(8.0, 6.0, 1.5, "inches"),
                Set.of("Watch", "Chronograph", "Luxury"),
                createCategory(
                        "48",
                        "Watches & Wearables",
                        "http://watches_wearables.jpg",
                        createCategory(
                                "7",
                                "Accessories",
                                "http://men_accessories.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Aviator Sunglasses",
                "Classic aviator sunglasses with UV protection.",
                6129.99,
                5599.99,
                "Ray-Ban",
                "https://images.pexels.com/photos/3839192/pexels-photo-3839192.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Metal",
                "Gold",
                Set.of(18, 20),
                new DimensionDto(6.0, 4.0, 1.5, "inches"),
                Set.of("Sunglasses", "Fashion", "UV Protection"),
                createCategory(
                        "49",
                        "Sunglasses & Frames",
                        "http://sunglasses_frames.jpg",
                        createCategory(
                                "7",
                                "Accessories",
                                "http://men_accessories.jpg",
                                createCategory(
                                        "2",
                                        "Men",
                                        "http://men.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Leggings",
                "Lightweight and breathable cotton fabric.",
                527.99,
                316.99,
                "Lyra",
                "https://images.pexels.com/photos/4498605/pexels-photo-4498605.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Black",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Trending"),
                createCategory(
                        "10",
                        "Leggings, Salwars & Churidars",
                        "http://leggings.jpg",
                        createCategory(
                                "4",
                                "Women - Indian Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Skirt",
                "Lightweight and breathable cotton fabric.",
                2799.99,
                1999.99,
                "Levi's",
                "https://images.pexels.com/photos/1149240/pexels-photo-1149240.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Blue",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits"),
                createCategory(
                        "16",
                        "Skirts & Palazzos",
                        "http://skirts.jpg",
                        createCategory(
                                "4",
                                "Indian & Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s palazzos",
                "Lightweight and breathable cotton fabric.",
                2799.99,
                1999.99,
                "Levi's",
                "https://images.pexels.com/photos/7203892/pexels-photo-7203892.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Black, Grey, Orange, Pink",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits"),
                createCategory(
                        "16",
                        "Skirts & Palazzos",
                        "http://palazzos.jpg",
                        createCategory(
                                "4",
                                "Indian & Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Sweatshirt",
                "Lightweight and breathable cotton wool fabric.",
                1999.99,
                1599.99,
                "U.S. Polo",
                "https://images.pexels.com/photos/6311392/pexels-photo-6311392.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Grey",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits"),
                createCategory(
                        "22",
                        "Sweaters & Sweatshirts",
                        "http://sweatshirt.jpg",
                        createCategory(
                                "4",
                                "Women - Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s Shrugs",
                "Lightweight and breathable cotton blend fabric.",
                1099.99,
                899.99,
                "Biba",
                "https://images.pexels.com/photos/4404049/pexels-photo-4404049.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Polyester",
                "Teal green",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits"),
                createCategory(
                        "21",
                        "Shrugs",
                        "http://shrugs.jpg",
                        createCategory(
                                "4",
                                "Women - Western Wear",
                                "http://western_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s kurtis",
                "Lightweight and breathable cotton blend fabric.",
                2099.99,
                1799.99,
                "W for Women",
                "https://images.pexels.com/photos/20777209/pexels-photo-20777209.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton blend",
                "Brown",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Jumpsuits"),
                createCategory(
                        "9",
                        "Kurtis & Tunics",
                        "http://kurtis_tunics.jpg",
                        createCategory(
                                "3",
                                "Women - Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s short kurtis",
                "Lightweight and breathable cotton fabric.",
                1099.99,
                799.99,
                "W for Women",
                "https://images.pexels.com/photos/5315339/pexels-photo-5315339.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton",
                "White",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Best Sellers"),
                createCategory(
                        "9",
                        "Kurtis & Tunics",
                        "http://kurtis_tunics.jpg",
                        createCategory(
                                "3",
                                "Women - Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));

        products.add(createProduct(
                null,
                "Women’s kurtis",
                "Lightweight and breathable cotton blend fabric.",
                1599.99,
                1299.99,
                "W for Women",
                "https://images.pexels.com/photos/19954265/pexels-photo-19954265.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "Cotton blend",
                "Light Pink",
                Set.of(34, 36, 38),
                new DimensionDto(30.0, 20.0, 1.2, "inches"),
                Set.of("Dress", "Casual", "Trending"),
                createCategory(
                        "9",
                        "Kurtis & Tunics",
                        "http://kurtis_tunics.jpg",
                        createCategory(
                                "3",
                                "Women - Indian Wear",
                                "http://indian_wear.jpg",
                                createCategory(
                                        "1",
                                        "Women",
                                        "http://women.jpg",
                                        null)))));
        return products;
    }

    private static ProductDto createProduct(String id,
                                     String name,
                                     String desc,
                                     Double price,
                                     Double discount,
                                     String brand,
                                     String image,
                                     String material,
                                     String color,
                                     Set<Integer> sizes,
                                     DimensionDto dim,
                                     Set<String> tags,
                                     CategoryDto category) {
        return ProductDto.builder()
                .productId(id)
                .name(name)
                .description(desc)
                .price(price)
                .discountedPrice(discount)
                .stock(new Random().nextInt(200) + 50)
                .imageUrl(image)
                .brand(brand)
                .weight(new Random().nextDouble() * 2 + 0.5)
                .dimension(dim)
                .material(material)
                .color(color)
                .sizes(sizes)
                .tags(CollectionUtils.isEmpty(tags) ? Set.of("Popular", "New Arrivals"): tags)
                .ratings(new RatingDto((4.2 + new Random().nextDouble() * 0.8),
                        new Random().nextInt(1000) + 100))
                .sku("SKU-" + brand.substring(0, 3).toUpperCase() + "-" + id)
                .priceUnit(price)
                .quantity(new Random().nextInt(200) + 50)
                .isActive(true)
                .category(category.getCategoryTitle())
                .categoryDto(category)
                .build();
    }

    private static CategoryDto createCategory(String id, String title, String image, CategoryDto parent) {
        return CategoryDto.builder()
                .categoryId(id)
                .categoryTitle(title)
                .imageUrl("https://example.com/categories/" + image)
                .parentCategory(parent)
                .build();
    }

}
