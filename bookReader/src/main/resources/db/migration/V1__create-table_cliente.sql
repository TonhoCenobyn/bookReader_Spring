create table clientes(
                         id BIGSERIAL not null primary key,
                         uuid UUID DEFAULT gen_random_uuid(),
                         nome varchar(50) not null,
                         email varchar(100) not null unique,
                         senha varchar(50) not null unique
)
