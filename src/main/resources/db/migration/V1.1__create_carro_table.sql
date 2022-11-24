create table if not exists carro(

    id SERIAL,
    nome varchar(50) not null,
    portas integer not null,
    cor varchar(50) not null,
    modelo varchar(50) not null,
    ano integer not null,
    placa varchar(50) not null,
    marca_id integer not null,

    constraint pk_carro primary key (id),
    constraint fk_carro_marca foreign key(marca_id) references marca(id)

);