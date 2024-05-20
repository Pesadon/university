using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace FilmszinhazProjekt
{
    public class Film
    {
        private string Cim;

        public Film(string cim)
        {
            Cim = cim;
        }

        public string GetCim()
        {
            return Cim;
        }
    }
}
