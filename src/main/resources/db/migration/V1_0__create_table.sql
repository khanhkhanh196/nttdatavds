DROP SCHEMA IF EXISTS `hb-product`;

CREATE SCHEMA `hb-product`;

use `hb-product`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `brand`;

create table `product` (
`product_id` int not null auto_increment,
`product_name` nvarchar(100),
`product_description` nvarchar(100) default null,
`stock` int default null,
`sold_quantity` int default null,
`brand_detail_id` int not null,

constraint `brand_fk_1` foreign key (`brand_detail_id`) 
references `brand` (`brand_id`),	

primary key (`product_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `brand` (
`brand_id` int not null auto_increment,
`brand_name` nvarchar(100) not null,
`slug` nvarchar(100) default null,

primary key (`brand_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


create table `review` (
`review_id` int not null auto_increment,
`username` nvarchar(100) not null,
`review_description` nvarchar(100) default null,
`rating` float default null,
`product_detail_id` int not null,

constraint `product_fk_1` foreign key (`product_detail_id`) 
references `product` (`product_id`),

primary key (`review_id`)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
