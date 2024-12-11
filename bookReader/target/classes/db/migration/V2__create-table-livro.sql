create table livros(
                         id BIGSERIAL not null primary key,
                         uuid UUID DEFAULT gen_random_uuid(),
                         titulo varchar(50) not null,
                         autor varchar(100) not null unique
);

