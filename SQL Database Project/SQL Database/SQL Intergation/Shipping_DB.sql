CREATE TABLE DISTRO
    (distro_id      numeric(4,0),
     distro_name    varchar(10),
     city           varchar(15),
     -- Maybe include connections? What else
     primary key (distro_id)

     );

CREATE TABLE Packages
    (package_id         varchar(15),
     last_distro_id     numeric(4,0),
     shipping_adress    varchar(15),
     shipping_city      varchar(15),
     shipping_zip       numeric(5,0),
     shipping_country   varchar(15),
     shipping_state     varchar(15),
     next_distro_id     numeric(4,0),
     out_for_del        numeric(1,0),
     status             varchar(15),
     account_id_ref     numeric(10,0),
     
     PRIMARY KEY (package_id)
     );
     
CREATE TABLE Account
    (Account_id                 numeric(10,0) NOT NULL,
     first_name                 varchar(10),
     last_name                  varchar(10),
     Middle_init                varchar(1),
     email                      varchar(20),
     password                   varchar(35),
     default_shipping_adress    varchar(15),
     default_shipping_city      varchar(15),
     default_shipping_zip       numeric(5,0),
     default_shipping_country   varchar(15),
     default_shipping_state     varchar(15),
     primary key (account_id)
     --foreign key (account_id) REFERENCES Packages,
     --foreign key (active_package_ids) REFERENCES Packages -- Might get rid of
     );
     
     --Auto Increment for Account_ID
     
    create sequence account_seq;
    
    create or replace trigger account_trg
    before insert on account
    for each row
    begin
        select account_seq.nextval into :NEW.account_id from dual;
    end;
    /
     
     CREATE TABLE Employee
     (employee_id               numeric(10,0),
     first_name                 varchar(10),
     last_name                  varchar(10),
     Middle_init                varchar(1),
     email                      varchar(20),
     password                   varchar(35),
     default_shipping_adress    varchar(15),
     default_shipping_city      varchar(15),
     default_shipping_zip       numeric(5,0),
     default_shipping_country   varchar(15),
     default_shipping_state     varchar(15),
     assigned_distro_id         numeric(4,0),
     primary key (employee_id),
     foreign key(assigned_distro_id) references distro(distro_id)
     );
     
alter table packages
add foreign key (last_distro_id) references distro;

alter table packages
add foreign key (next_distro_id) references distro;

alter table packages
add foreign key (account_id_ref) references account(account_id);


