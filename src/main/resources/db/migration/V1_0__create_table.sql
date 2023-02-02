


create table `product` (
`product_id` int not null auto_increment,
`product_name` nvarchar(100),
`short_desc` nvarchar(100) default null,
`stock` int default null,
`price` double default null,
`sold` int default null,

primary key (`product_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `category` (
`category_id` int not null auto_increment,
`category_name` nvarchar(100) not null,
`slug` nvarchar(100) default null unique,

primary key (`category_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `file` (
`file_id` int not null auto_increment,
`file_name` nvarchar(100) not null,
`url` nvarchar(100) default null,

primary key (`file_id`)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `category_product` (
`id` int not null auto_increment,
`category_references_product_id` int ,
`product_references_category_id` int,

primary key (`id`),

constraint `product_fk_1` foreign key (`product_references_category_id`)
references `product` (`product_id`),

constraint `category_fk_1` foreign key (`category_references_product_id`)
references `category` (`category_id`)
);

create table `product_file` (
`id` int not null auto_increment,
`product_references_file_id` int ,
`file_references_product_id` int,

primary key (`id`),

constraint `product_fk_3` foreign key (`product_references_file_id`)
references `product` (`product_id`),

constraint `file_fk_1` foreign key (`file_references_product_id`)
references `file` (`file_id`)
);
