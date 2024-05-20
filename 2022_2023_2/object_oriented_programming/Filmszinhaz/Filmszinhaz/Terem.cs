using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FilmszinhazProjekt
{
    public abstract class Terem
    {
        private int Teremszam;
        private int Sor;
        private int Oszlop;

        public Terem(int teremszam,int sor, int oszlop)
        {
            Teremszam = teremszam;
            Sor = sor;
            Oszlop = oszlop;
        }

        public int GetTeremszam()
        {
            return Teremszam;
        }

        public int GetSor()
        {
            return Sor;
        }

        public int GetOszlop()
        {
            return Oszlop;
        }

        public abstract string Kategoria();
    }

    public class Kicsi : Terem
    {
        public Kicsi(int teremszam,int sor, int oszlop) : base(teremszam,sor, oszlop)
        {
        }

        public override string Kategoria()
        {
            return "Kicsi";
        }
    }

    public class Nagy : Terem
    {
        public Nagy(int teremszam, int sor, int oszlop) : base(teremszam, sor, oszlop)
        {
        }

        public override string Kategoria()
        {
            return "Nagy";
        }
    }

    public class VIP : Terem
    {
        public VIP(int teremszam, int sor, int oszlop) : base(teremszam, sor, oszlop)
        {
        }

        public override string Kategoria()
        {
            return "VIP";
        }
    }
}
