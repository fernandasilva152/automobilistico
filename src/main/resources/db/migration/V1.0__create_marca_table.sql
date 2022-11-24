create table if not exists marca (

    id SERIAL not null,
    nome varchar(50) not null,

    constraint pk_marca primary key(id)

);