
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

              
INSERT INTO prevoznik (id, adresa, naziv, pib) VALUES (1, 'Vladike Cirica 1', 'Ogi trans ', '22222');
INSERT INTO prevoznik (id, adresa, naziv, pib) VALUES (2, 'Vladike Cirica 2', 'Ogi trans 2 ', '33333');
INSERT INTO prevoznik (id, adresa, naziv, pib) VALUES (3, 'Vladike Cirica 3', 'Ogi trans 3', '99999');

INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (1, 100, 100, 'Novi Sad', '2020-06-21 20:00', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (2, 110, 200, 'Beograd', '2020-06-22 21:00', 2);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (3, 120, 300, 'Vranje', '2020-06-23 22:00', 3);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (4, 130, 400, 'Kragujevac', '2020-06-24 23:00', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (5, 140, 420, 'Kragujevac', '2020-06-24 22:00', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (6, 150, 430, 'Kragujevac', '2020-06-24 21:00', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id) VALUES (7, 160, 440, 'Kragujevac', '2020-06-24 20:00', 1);

INSERT INTO rezervacija (id, broj_karata, linija_id) VALUES (1, 10, 1);
INSERT INTO rezervacija (id, broj_karata, linija_id) VALUES (2, 10, 2);
INSERT INTO rezervacija (id, broj_karata, linija_id) VALUES (3, 10, 3);

              