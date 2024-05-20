using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FilmszinhazProjekt
{
    public class Hely
    {
        private char Sor;
        private int Oszlop;
        private Jegy Jegy;

        public Hely(char sor, int oszlop, Jegy jegy)
        {
            Sor = sor;
            Oszlop = oszlop;
            Jegy = jegy;
        }

        public char GetSor()
        {
            return Sor;
        }

        public int GetOszlop()
        {
            return Oszlop;
        }

        public Jegy GetJegy()
        {
            return Jegy;
        }
    }
}
