create table receitas (
    id int primary key auto_increment not null,
    descricao varchar(45) not null unique ,
    valor decimal(10,2) not null,
    data DATE not null
);