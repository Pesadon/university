/*
 * Készítette: Perczel-Szabó Dániel
 * Neptun: GQF4SF
 * E-mail: gqf4sf@inf.elte.hu
 * Feladat: Legváltozóbb települések
 */

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LegvaltozobbTelepulesek
{
    class Program
    {
        public static int[,] temps;
        public static int places;
        public static int days;

        static void Main(string[] args)
        {
            Beolvas();
            Kiertekel();

            Console.ReadKey();
        }

        static void Beolvas()
        {
            string[] firstLine;
            bool placesNum = false;
            bool daysNum = false;

            do
            {
                firstLine = Console.ReadLine().Split(' ');
                placesNum = int.TryParse(firstLine[0], out places);
                daysNum = int.TryParse(firstLine[1], out days);

                if(!placesNum || !daysNum || places < 1 || places > 1000 || days < 1 || days > 1000)
                    Console.WriteLine("Hibas input");
            }
            while (!placesNum || !daysNum || places < 1 || places > 1000 || days < 1 || days > 1000);

            temps = new int[places, days];

            bool allGood=false;

            while (!allGood)
            {
                allGood = true;

                for (int i = 0; i < places; i++)
                {
                    string[] mfl = Console.ReadLine().Split(' ');

                    bool tempNum = false;

                    for (int j = 0; j < days; j++)
                    {
                        tempNum = int.TryParse(mfl[j], out temps[i, j]);

                        if (!tempNum || temps[i,j]<-50 || temps[i, j] > 50)
                        {
                            allGood = false;
                            break;
                        }
                    }

                    if (!allGood)
                        break;
                }

                if(!allGood)
                    Console.WriteLine("Hibas input");
            }
        }

        static int MaxDif()
        {
            int maxdif = 0;

            for(int i=0;i<places;i++)
                for(int j = 1; j < days; j++)
                {
                    if (Math.Abs(temps[i, j] - temps[i, j - 1]) > maxdif)
                        maxdif = Math.Abs(temps[i, j] - temps[i, j - 1]);
                }

            return maxdif;
        }

        static void Kiertekel()
        {
            int[] res = new int[days];
            int db = 0;
            int maxDif = MaxDif();

            for(int i=0;i<places;i++)
                for(int j = 1; j < days; j++)
                {
                    if(Math.Abs(temps[i, j] - temps[i, j - 1]) == maxDif)
                    {
                        res[db] = i + 1;
                        db++;
                        break;
                    }
                }

            Console.Write(db);

            for (int i = 0; i < db; i++)
                Console.Write(" " + res[i]);
        }
    }
}
