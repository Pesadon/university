using FilmszinhazProjekt;

namespace FilmszinhazTest
{
    public class Tests
    {
        static List<Terem> termek = Termek("termek.txt");
        static List<Film> filmek = Filmek("filmek.txt");
        static List<Eloadas> eloadasok = Eloadasok("eloadasok.txt", "termek.txt");

        static Filmszinhaz filmszinhaz = new Filmszinhaz(filmek, termek, eloadasok);

        public static void Feltolt(string condition)
        {
            FoglaltHelyek("foglalasok.txt");

            if (condition == "foglalas")
                EladottHelyek("foglalasok.txt");
            else
                EladottHelyek("vasarlasok.txt");
        }

        public static List<Terem> Termek(string path)
        {
            List<Terem> termek = new List<Terem>();

            FileStream f = new FileStream(path, FileMode.Open);
            StreamReader sr = new StreamReader(f);

            while (!sr.EndOfStream)
            {
                string[] line = sr.ReadLine().Split(';');
                switch (line[3])
                {
                    case "Kicsi":
                        termek.Add(new Kicsi(int.Parse(line[0]), int.Parse(line[1]), int.Parse(line[2])));
                        break;
                    case "Nagy":
                        termek.Add(new Nagy(int.Parse(line[0]), int.Parse(line[1]), int.Parse(line[2])));
                        break;
                    case "VIP":
                        termek.Add(new VIP(int.Parse(line[0]), int.Parse(line[1]), int.Parse(line[2])));
                        break;
                    default: break;
                }
            }

            sr.Close();
            f.Close();

            return termek;
        }

        public static List<Film> Filmek(string path)
        {
            List<Film> filmek = new List<Film>();

            FileStream f = new FileStream(path, FileMode.Open);
            StreamReader sr = new StreamReader(f);

            while (!sr.EndOfStream)
            {
                filmek.Add(new Film(sr.ReadLine()));
            }

            sr.Close();
            f.Close();

            return filmek;
        }

        public static List<Eloadas> Eloadasok(string eloadasokPath, string termekPath)
        {
            List<Eloadas> eloadasok = new List<Eloadas>();

            FileStream f = new FileStream(eloadasokPath, FileMode.Open);
            StreamReader sr = new StreamReader(f);

            while (!sr.EndOfStream)
            {
                string[] line = sr.ReadLine().Split(';');

                Terem terem = new Kicsi(0, 0, 0);

                foreach (Terem teremS in Termek(termekPath))
                    if (teremS.GetTeremszam() == int.Parse(line[2]))
                        terem = teremS;


                eloadasok.Add(new Eloadas(new Film(line[1]), DateTime.Parse(line[0]), terem));
            }

            sr.Close();
            f.Close();

            return eloadasok;
        }

        public static void FoglaltHelyek(string path)
        {
            FileStream f = new FileStream(path, FileMode.Open);
            StreamReader sr = new StreamReader(f);

            foreach(Eloadas eloadasErase in eloadasok)
            {
                eloadasErase.SetFoglaltHelyek(new List<Hely>());
            }

            while (!sr.EndOfStream)
            {
                string[] line = sr.ReadLine().Split(';');

                switch (line[4])
                {
                    case "Gyerek":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Lefoglal(char.Parse(line[1]), int.Parse(line[2]), new Gyerek(int.Parse(line[3])));
                            break;
                        }
                        break;
                    case "Diak":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Lefoglal(char.Parse(line[1]), int.Parse(line[2]), new Diak(int.Parse(line[3])));
                            break;
                        }
                        break;
                    case "Felnott":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Lefoglal(char.Parse(line[1]), int.Parse(line[2]), new Felnott(int.Parse(line[3])));
                            break;
                        }
                        break;
                    case "Nyugdijas":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Lefoglal(char.Parse(line[1]), int.Parse(line[2]), new Nyugdijas(int.Parse(line[3])));
                            break;
                        }
                        break;
                    case "Torzstag":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Lefoglal(char.Parse(line[1]), int.Parse(line[2]), new Gyerek(int.Parse(line[3])));
                            break;
                        }
                        break;
                    default: break;
                }
            }

            sr.Close();
            f.Close();
        }

        public static void EladottHelyek(string path)
        {
            FileStream f = new FileStream(path, FileMode.Open);
            StreamReader sr = new StreamReader(f);

            foreach (Eloadas eloadasErase in eloadasok)
            {
                eloadasErase.SetEladottHelyek(new List<Hely>());
            }

            while (!sr.EndOfStream)
            {
                string[] line = sr.ReadLine().Split(';');

                switch (line[4])
                {
                    case "Gyerek":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Megvesz(char.Parse(line[1]), int.Parse(line[2]), new Gyerek(int.Parse(line[3])));
                        }
                        break;
                    case "Diak":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Megvesz(char.Parse(line[1]), int.Parse(line[2]), new Diak(int.Parse(line[3])));
                        }
                        break;
                    case "Felnott":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Megvesz(char.Parse(line[1]), int.Parse(line[2]), new Felnott(int.Parse(line[3])));
                        }
                        break;
                    case "Nyugdijas":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Megvesz(char.Parse(line[1]), int.Parse(line[2]), new Nyugdijas(int.Parse(line[3])));
                        }
                        break;
                    case "Torzstag":
                        foreach (Eloadas eloadas in eloadasok)
                        {
                            if (eloadas.GetTerem().GetTeremszam() == int.Parse(line[0]))
                                eloadas.Megvesz(char.Parse(line[1]), int.Parse(line[2]), new Gyerek(int.Parse(line[3])));
                        }
                        break;
                    default: break;
                }
            }

            sr.Close();
            f.Close();
        }

        [Test]
        public void Test1()
        {
            filmszinhaz = new Filmszinhaz(filmek, termek, eloadasok);

            Feltolt("");

            Filmszinhaz fsz = new Filmszinhaz(filmek, termek, eloadasok);
            string maxfilm = fsz.LegnezettebbFilm().GetCim();

            Assert.That(maxfilm, Is.EqualTo("The Shawshank Redemption"));
        }

        [Test]
        public void Test2()
        {
            Filmszinhaz fsz = new Filmszinhaz(filmek, termek, eloadasok);
            Eloadas keresettEloadas = new Eloadas(new Film("Pulp Fiction"), new DateTime(2023, 6, 8, 20, 30, 0), new Kicsi(4, 9, 7));

            Assert.That(fsz.HelyekSzama(keresettEloadas),Is.EqualTo((11,0,52)));
        }

        [Test]
        public void Test3()
        {
            Feltolt("foglalas");

            Filmszinhaz fsz = new Filmszinhaz(filmek, termek, eloadasok);
            Eloadas keresettEloadas = new Eloadas(new Film("Pulp Fiction"), new DateTime(2023, 6, 8, 20, 30, 0), new Kicsi(4, 9, 7));

            Assert.That(fsz.HelyekSzama(keresettEloadas), Is.EqualTo((10, 0, 53)));
        }
    }
}