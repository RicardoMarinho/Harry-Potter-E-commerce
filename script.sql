select now();
SET global time_zone = '+1:00';

--
-- Create schema store
--

CREATE DATABASE IF NOT EXISTS store;
USE store;

-- Create table account
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int8 auto_increment,
  `address` varchar(255) default '',
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ativo` bit default true,
  `email` varchar(100) NOT NULL,
  `phone` varchar(10) default '',
  `register` datetime default now(),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table products
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int8 auto_increment,
  `name` varchar(255) NOT NULL,
  `description` text,
  `category` varchar(255) NOT NULL,
  `subcategory` varchar(255) NOT NULL,
  `stock` int8 NOT NULL,
  `price` float8 NOT NULL,
  `imgUrl` varchar(255) default 'app/img/product-img/default.png',
  `isPopular` bit default false,
  `register` datetime default now(),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table payments
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments` (
  `id` int8 auto_increment,
  `userId` int8 NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `cardNumber` varchar(255) NOT NULL,
  `monthExpire` int8 NOT NULL,
  `yearExpire` int8 NOT NULL,
  `cvv` int8 NOT NULL,
  `active` bit default false,
  `register` datetime default now(),
  PRIMARY KEY  (`id`),
  KEY `FK_account_payment_id` (`userId`),
  CONSTRAINT `fk_account_payments` FOREIGN KEY (`userId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int8 auto_increment,
  `userId` int8 NOT NULL,
  `register` datetime default now(),
  PRIMARY KEY  (`id`),
  KEY `FK_account_cart_id` (`userId`),
  CONSTRAINT `fk_account_cart` FOREIGN KEY (`userId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table itemsCart
DROP TABLE IF EXISTS `itemsCart`;
CREATE TABLE `itemsCart` (
  `id` int8 auto_increment,
  `cartId` int8 NOT NULL,
  `productId` int8,
  `qty` int8 default 1,
  `register` datetime default now(),
  PRIMARY KEY  (`id`),
  KEY `FK_cart_items_id` (`cartId`),
  KEY `FK_product_items_id` (`productId`),
  CONSTRAINT `fk_cart_items` FOREIGN KEY (`cartId`) REFERENCES `cart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_items` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int8 auto_increment,
  `accountId` int8 NOT NULL,
  `status` varchar(50) NOT NULL default 'Pending',
  `shipAddress` varchar(255) NOT NULL,
  `total` float NOT NULL,
  `ordered` datetime default now(),
  `shipped` datetime,
  PRIMARY KEY  (`id`),
  KEY `FK_order_account_id` (`accountId`),
  CONSTRAINT `fk_order_account_id` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Next step
CREATE TRIGGER dateinsert BEFORE INSERT ON `orders`
FOR EACH ROW
SET NEW.shipped =  DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL 5 DAY);

-- Next step
-- Create table orders
DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE `orderdetails` (
  `orderId` int8 NOT NULL,
  `productId` int8 NOT NULL,
  `productname` varchar(255) NOT NULL,
  `qty` int8 NOT NULL default 1,
  `productprice` float8 NOT NULL,
  `register` datetime default now(),
  PRIMARY KEY  (`orderId`, `productId`),
  KEY `FK_order_id` (`orderId`),
  KEY `FK_product_id` (`productId`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_id` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Next Step
-- Insert products
INSERT INTO `store`.`products` (`name`, `description`, `category`, `subcategory`, `stock`, `price`, `imgUrl`, `isPopular`) 
VALUES 
('Long Black Robe - Slytherin', 'Become the ultimate Slytherin student with this beautiful long black Slytherin Robe, complete with coloured lining and an embroidered house crest patch.', 'clothing', 'costumes', '10', '72.64', 'app/img/product-img/product-3.png', true),
('Long Black Robe - Gryffindor', 'Become the ultimate Gryffindor student with this beautiful long, black Gryffindor Robe, complete with coloured lining and an embroidered house crest patch.', 'clothing', 'costumes', '5', '72.64', 'app/img/product-img/product-4.png', true),
('Long Black Robe - Ravenclaw', 'Become the ultimate Ravenclaw student with this beautiful long black Ravenclaw Robe, complete with coloured lining and an embroidered house crest patch.', 'clothing', 'costumes', '0', '72.64', 'app/img/product-img/product-2.png', true),
('Long Black Robe - Hufflepuff', 'Become the ultimate Hufflepuff student with this beautiful long black Hufflepuff Robe, complete with coloured lining and an embroidered house crest patch.', 'clothing', 'costumes', '3', '72.64', 'app/img/product-img/product-1.png', true),
('Lord Voldemort Wand Pen and Bookmark', 'The pen is crafted to look like a miniature version of Voldemort\'s wand and measures 7 1/4 inches long. The bookmark features an image of Voldemort from the films and measures 6 3/4 x 2 1/4 inches.', 'souvenirs', 'bookmarks', '10', '13.93', 'app/img/product-img/product-15.png,app/img/product-img/product-15-back.png', false),
('Severus Snape Wand Pen and Bookmark', 'The pen is crafted to look like a miniature version of Snape\'s wand and measures 7 1/4 inches long. The bookmark features an image of Snape from the films and measures 6 3/4 x 2 1/4 inches.', 'souvenirs', 'bookmarks', '3', '13.93', 'app/img/product-img/product-14.png,app/img/product-img/product-14-back.png', false),
('New Edition Box Set Harry Potter Paperback Collection', 'A new edition of the Harry Potter books by J.K Rowling featuring brand new illustrated covers, presented in a beautiful display box. The collection contains all seven Harry Potter books in paperback.', 'departments', 'books', '10', '66.85', 'app/img/product-img/product-13.png,app/img/product-img/product-13-back.png', false),
('New Edition Harry Potter and The Deathly Hallows (Paperback)', 'When Dumbledore arrives at Privet Drive one summer night to collect Harry Potter, his wand hand is blackened and shrivelled, but he does not reveal why. Secrets and suspicion are spreading through the wizarding world, and Hogwarts itself is not safe. Harry is convinced that Malfoy bears the Dark Mark: there is a Death Eater amongst them. Harry will need powerful magic and true friends as he explores Voldemort\'s darkest secrets, and Dumbledore prepares him to face his destiny.', 'departments', 'books', '3', '10.02', 'app/img/product-img/product-12.png,app/img/product-img/product-12-back.png', false),
('New Edition Harry Potter and Philosopher\'s Stone (Paperback)', 'Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry\'s eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin!', 'departments', 'books', '1', '8.90', 'app/img/product-img/product-11.png,app/img/product-img/product-11-back.png', false),
('Hufflepuff Quidditch Jumper', 'Cheer on your favourite Hogwarts house Quidditch team with this Hufflepuff Quidditch jumper. Featuring an embroidered Hufflepuff house crest patch on the left chest, and reproduced in the yellow and black colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ', 'clothing', 'knitwear', '7', '50.15', 'app/img/product-img/product-6.png,app/img/product-img/product-6-back.png', false),
('Gryffindor Quidditch Jumper', 'Cheer on your favourite Hogwarts house Quidditch team with this Gryffindor Quidditch jumper. Featuring an embroidered Gryffindor house crest patch on the left chest, and reproduced in the scarlet and gold colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ', 'clothing', 'knitwear', '10', '50.15', 'app/img/product-img/product-7.png,app/img/product-img/product-7-back.png', false),
('Ravenclaw Quidditch Jumper', 'Cheer on your favourite Hogwarts house Quidditch team with this Ravenclaw Quidditch jumper. Featuring an embroidered Ravenclaw house crest patch on the left chest, and reproduced in the blue and silver colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ', 'clothing', 'knitwear', '5', '50.15', 'app/img/product-img/product-9.png,app/img/product-img/product-9-back.png', false);

-- Next step
