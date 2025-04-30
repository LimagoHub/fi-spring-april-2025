drop table if exists `tbl_personen`;
CREATE TABLE `tbl_personen` (
        `id` uuid NOT NULL,
        `nachname` varchar(100) NOT NULL,
        `vorname` varchar(100) DEFAULT NULL,
        PRIMARY KEY (`id`)
) ;