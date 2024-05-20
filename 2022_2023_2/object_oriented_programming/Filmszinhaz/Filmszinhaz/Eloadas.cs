using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FilmszinhazProjekt
{
    public class Eloadas
    {
        private Film Film;
        private DateTime Idopont;
        private Terem Terem;
        private List<Hely> FoglaltHelyek = new List<Hely>();
        private List<Hely> EladottHelyek = new List<Hely>();

        public Eloadas(Film film, DateTime idopont, Terem terem)
        {
            Film = film;
            Idopont = idopont;
            Terem = terem;
        }

        public Film GetFilm()
        {
            return Film;
        }

        public DateTime GetIdopont()
        {
            return Idopont;
        }

        public Terem GetTerem()
        {
            return Terem;
        }

        public List<Hely> GetFoglaltHelyek()
        {
            return FoglaltHelyek;
        }

        public void SetFoglaltHelyek(List<Hely> foglaltHelyek)
        {
            FoglaltHelyek=foglaltHelyek;
        }

        public List<Hely> GetEladottHelyek()
        {
            return EladottHelyek;
        }

        public void SetEladottHelyek(List<Hely> eladottHelyek)
        {
            EladottHelyek = eladottHelyek;
        }

        public void Lefoglal(char sor, int oszlop, Jegy jegy)
        {
            Hely uj=new Hely(sor, oszlop, jegy);

            if (FoglaltHelyek!=null && FoglaltHelyek.Contains(uj))
                return;

            FoglaltHelyek.Add(uj);
        }

        public void Megvesz(char sor, int oszlop,Jegy jegy)
        {
            Hely uj = new Hely(sor, oszlop, jegy);

            if (EladottHelyek != null && EladottHelyek.Contains(uj))
                return;

            if(FoglaltHelyek!=null)
                foreach(Hely fh in FoglaltHelyek)
                {
                    if (fh.GetSor() == sor && fh.GetOszlop() == oszlop && fh.GetJegy() != jegy)
                        return;
                    else if(fh.GetSor() == sor && fh.GetOszlop() == oszlop && fh.GetJegy() == jegy)
                        FoglaltHelyek.Remove(fh);
                }

            EladottHelyek.Add(uj);
        }
    }
}
