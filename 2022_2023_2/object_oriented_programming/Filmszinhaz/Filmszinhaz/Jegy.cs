using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FilmszinhazProjekt
{
    public abstract class Jegy
    {
        public int Alapar;

        public Jegy(int alapar)
        {
            Alapar = alapar;
        }

        public virtual bool isGyerek()
        {
            return false;
        }

        public virtual bool isDiak()
        {
            return false;
        }

        public virtual bool isFelnott()
        {
            return false;
        }

        public virtual bool isNyugdijas()
        {
            return false;
        }

        public virtual bool isTorzstag()
        {
            return false;
        }

        public abstract int GetArKisTerem();
        public abstract int GetArNagyTerem();
        public abstract int GetArVIP();
    }

    public class Gyerek : Jegy
    {
        public Gyerek(int alapar) : base(alapar)
        {
        }

        public override bool isGyerek()
        {
            return true;
        }

        public override int GetArKisTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.6));
        }

        public override int GetArNagyTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.6));
        }

        public override int GetArVIP()
        {
            return Alapar;
        }
    }

    public class Diak : Jegy
    {
        public Diak(int alapar) : base(alapar)
        {
        }

        public override bool isDiak()
        {
            return true;
        }

        public override int GetArKisTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.7));
        }

        public override int GetArNagyTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.8));
        }

        public override int GetArVIP()
        {
            return Alapar;
        }
    }

    public class Felnott : Jegy
    {
        public Felnott(int alapar) : base(alapar)
        {
        }

        public override bool isFelnott()
        {
            return true;
        }

        public override int GetArKisTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.7));
        }

        public override int GetArNagyTerem()
        {
            return Alapar;
        }

        public override int GetArVIP()
        {
            return Alapar;
        }
    }

    public class Nyugdijas : Jegy
    {
        public Nyugdijas(int alapar) : base(alapar)
        {
        }

        public override bool isNyugdijas()
        {
            return true;
        }

        public override int GetArKisTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.7));
        }

        public override int GetArNagyTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.8));
        }

        public override int GetArVIP()
        {
            return Alapar;
        }
    }

    public class Torzstag : Jegy
    {
        public Torzstag(int alapar) : base(alapar)
        {
        }

        public override bool isTorzstag()
        {
            return true;
        }

        public override int GetArKisTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.7));
        }

        public override int GetArNagyTerem()
        {
            return Convert.ToInt32(Math.Round(Alapar * 0.7));
        }

        public override int GetArVIP()
        {
            return Alapar;
        }
    }
}
