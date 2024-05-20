using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;

namespace FilmszinhazProjekt
{
    public class Filmszinhaz
    {
        private List<Film> Filmek;
        private List<Terem> Termek;
        private List<Eloadas> Eloadasok;

        public Filmszinhaz(List<Film> filmek, List<Terem> termek, List<Eloadas> eloadasok)
        {
            Filmek = filmek;
            Termek = termek;
            Eloadasok = eloadasok;
        }

        public (int,int,int) HelyekSzama(Eloadas eloadas)
        {
            foreach(Eloadas ea in Eloadasok)
            {
                if (ea.GetFilm().GetCim() == eloadas.GetFilm().GetCim() && ea.GetTerem().GetTeremszam() == eloadas.GetTerem().GetTeremszam() && ea.GetIdopont() == eloadas.GetIdopont())
                {
                    eloadas.SetFoglaltHelyek(ea.GetFoglaltHelyek());
                    eloadas.SetEladottHelyek(ea.GetEladottHelyek());
                }    
            }

            int teremMeret = eloadas.GetTerem().GetSor() * eloadas.GetTerem().GetOszlop();
            int szabadHelyek = teremMeret - eloadas.GetFoglaltHelyek().Count() - eloadas.GetEladottHelyek().Count();

            return (eloadas.GetEladottHelyek().Count(), eloadas.GetFoglaltHelyek().Count(), szabadHelyek);
        }

        public Film LegnezettebbFilm()
        {
            int maxNezo = 0;
            Film maxFilm = Filmek[0];

            foreach(Film film in Filmek)
            {
                int nezok = 0;

                foreach(Eloadas e in Eloadasok)
                {
                    if (e.GetFilm().GetCim() == film.GetCim())
                        nezok += e.GetEladottHelyek().Count();
                }

                if (nezok > maxNezo)
                {
                    maxNezo = nezok;
                    maxFilm = film;
                }
            }

            return maxFilm;
        }
    }
}
