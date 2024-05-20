#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <errno.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_POEM_LENGTH 100
#define FILENAME "locsolovers.txt"

void listPoems() {
    FILE *file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Nincsenek mentett versek.\n");
        return;
    }

    char poem[MAX_POEM_LENGTH];
    int count = 1;
    while (fgets(poem, MAX_POEM_LENGTH, file) != NULL) {
        printf("%d. %s", count, poem);
        count++;
    }
    fclose(file);
}

void addPoem() {
    char poem[MAX_POEM_LENGTH];
    printf("Írja be az új verset: ");
    fgets(poem, MAX_POEM_LENGTH, stdin);

    FILE *file = fopen(FILENAME, "a");
    if (file == NULL) {
        printf("Hiba történt a fájl megnyitásakor.\n");
        return;
    }
    fprintf(file, "%s", poem);
    fclose(file);
}

void deletePoem(int index) {
    FILE *file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Nincs ilyen fájl.\n");
        return;
    }

    char tempFile[] = "temp.txt";
    FILE *temp = fopen(tempFile, "w");
    if (temp == NULL) {
        fclose(file);
        printf("Hiba történt az ideiglenes fájl létrehozásakor.\n");
        return;
    }

    char poem[MAX_POEM_LENGTH];
    int count = 1;
    while (fgets(poem, MAX_POEM_LENGTH, file) != NULL) {
        if (count != index) {
            fprintf(temp, "%s", poem);
        }
        count++;
    }

    fclose(file);
    fclose(temp);

    remove(FILENAME);
    rename(tempFile, FILENAME);

    printf("A vers törölve.\n");
}

void modifyPoem(int index) {
    FILE *file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Nincs ilyen fájl.\n");
        return;
    }

    char tempFile[] = "temp.txt";
    FILE *temp = fopen(tempFile, "w");
    if (temp == NULL) {
        fclose(file);
        printf("Hiba történt az ideiglenes fájl létrehozásakor.\n");
        return;
    }

    char poem[MAX_POEM_LENGTH];
    int count = 1;
    while (fgets(poem, MAX_POEM_LENGTH, file) != NULL) {
        if (count == index) {
            char newPoem[MAX_POEM_LENGTH];
            printf("Írja be az új verset: ");
            fgets(newPoem, MAX_POEM_LENGTH, stdin);
            fprintf(temp, "%s", newPoem);
        } else {
            fprintf(temp, "%s", poem);
        }
        count++;
    }

    fclose(file);
    fclose(temp);

    remove(FILENAME);
    rename(tempFile, FILENAME);

    printf("A vers módosítva.\n");
}

//2. beadandó

struct message
{
    long mtype;
    char mtext[400];
};

void handler(int sigint)
{
    printf("Szülő jelzést kap a gyerektől\n");
}

char* getPoem(int lineIndex)
{
    FILE *file = fopen(FILENAME, "r");
    char* line = NULL;
    size_t len = NULL;

    for (int i = 1; i <= lineIndex; ++i)
    {
        getline(&line, &len, file);
    }

    for (int i = 1; i <= len; ++i)
    {
        if (*(line+i) == '\n')
        {
            *(line+i) = '\0';
            break;
        }
    }

    fclose(file);
    return line;
}

void locsolas()
{
    signal(SIGTERM, handler);

    int pipefd[2];
    if(pipe(pipefd) == -1)
    {
        perror("Pipe open error");
        exit(EXIT_FAILURE);
    }
    char m[MAX_POEM_LENGTH];

    int messageQueue, status;
    key_t key;
    key = ftok("./a.out", 1);
    messageQueue = msgget(key, 0600 | IPC_CREAT);

    if (messageQueue < 0)
    {
        perror("msgget");
        exit(EXIT_FAILURE);
    }

    char* kids[]={"Ödön","Ubul","József","Salvador"};
    int randKidIndex = rand() % 3;
    char* chosenKid=kids[randKidIndex];

    FILE *file = fopen(FILENAME, "r");
    if (file == NULL) {
        printf("Hiba történt a fájl megnyitásakor.\n");
        return;
    }

    pid_t child = fork();

    if (child < 0)
    {
        perror("Fork error");
        exit(EXIT_FAILURE);
    }
    else if(child>0)
    {
        pause();
        printf("Mama jelzést kap, hogy a gyereke (%s) megérkezett locsolni\n",chosenKid);
        close(pipefd[0]);

        int numOfPoems=0;
        char c = fgetc(file);
        while (c != EOF)
        {
            if (c == '\n')
                numOfPoems++;
            c = fgetc(file);
        }

        int firsRandomPoemIndex=rand() % numOfPoems + 1;
        char* randomPoem;
        int count=1;
        randomPoem=getPoem(firsRandomPoemIndex);
        int length = strlen(randomPoem) + 1;
        write(pipefd[1], &length, sizeof(length));
        write(pipefd[1], randomPoem, length);
        printf("Mama elküldte csövön az 1. random verset: %s\n", randomPoem);

        int secondRandomPoemIndex=0;
        while(secondRandomPoemIndex==0 || secondRandomPoemIndex==firsRandomPoemIndex){
            secondRandomPoemIndex=rand() % numOfPoems + 1;
        }
        randomPoem=getPoem(secondRandomPoemIndex);
        write(pipefd[1], randomPoem, strlen(randomPoem) + 1);
        close(pipefd[1]);
        printf("Mama elküldte csövön az 2. random verset: %s\n", randomPoem);

        sleep(3);
        struct message ms;
        int status;
        status = msgrcv(messageQueue, &ms, MAX_POEM_LENGTH, 5, 0);
        if (status < 0)
            perror("msgrcv");
        printf("Mama megkapta üzenetsoron a kiválasztott verset: %s\n\n", ms.mtext);

        wait(NULL);
        printf("Gyerek (%s) hazatért\n",chosenKid);
        status = msgctl(messageQueue, IPC_RMID, NULL);
        if (status < 0)
            perror("msgctl");
    }
    else
    {
        printf("Gyerek (%s) elindult locsolni.\n", chosenKid);
        sleep(2);
        printf("Gyerek (%s) jelez a mamának, hogy megérkezett locsolni.\n\n", chosenKid);
        kill(getppid(), SIGTERM);
        sleep(3);
        close(pipefd[1]);

        int length;
        char m[MAX_POEM_LENGTH];
        read(pipefd[0],&length,sizeof(length));
        read(pipefd[0],m,length);
        printf("Gyerek (%s) megkapta csövön keresztül az első verset: %s\n",chosenKid,m);

        char m2[MAX_POEM_LENGTH];
        read(pipefd[0],m2,sizeof(m2));
        printf("Gyerek (%s) megkapta csövön keresztül a második verset: %s\n",chosenKid,m2);

        close(pipefd[0]);

        int status;
        const struct message msg={5,NULL};

        int chosenPoemIndex=rand() % 2 + 1;
        switch(chosenPoemIndex){
            case 1:
                strcpy(msg.mtext,m);
                status=msgsnd(messageQueue,&msg,strlen(msg.mtext)+1,0);
                if(status<0)
                    perror("msgsnd");

                printf("Gyerek (%s) elküldte üzenetsoron a választott verset\n",chosenKid);
                sleep(2);
                printf("Gyerek szaval: %s \n Szabad-e locsolni?\n",m);
                break;
            case 2:
                strcpy(msg.mtext,m2);
                status=msgsnd(messageQueue,&msg,strlen(msg.mtext)+1,0);
                if(status<0)
                    perror("msgsnd");

                printf("Gyerek (%s) elküldte üzenetsoron a választott verset\n",chosenKid);
                sleep(2);
                printf("Gyerek szaval: %s \n Szabad-e locsolni?\n",m2);
                break;
        }

        sleep(2);
        printf("Gyerek végzett a locsolással, hazatér a mamájához.\n");
        sleep(2);
        exit(EXIT_SUCCESS);
    }
}

int main() {
    srand((unsigned int)time(NULL));

    int choice, index;
    do {
        printf("\n1. Vers hozzáadása\n");
        printf("2. Verseket listáz\n");
        printf("3. Vers törlése\n");
        printf("4. Vers módosítása\n");
        printf("5. Fiú elküldése locsolni\n");
        printf("0. Kilépés\n");
        printf("Válasszon: ");
        scanf("%d", &choice);
        getchar();

        switch (choice) {
            case 1:
                addPoem();
                break;
            case 2:
                listPoems();
                break;
            case 3:
                printf("Adja meg a törlendő vers sorszámát: ");
                scanf("%d", &index);
                getchar();
                deletePoem(index);
                break;
            case 4:
                printf("Adja meg a módosítandó vers sorszámát: ");
                scanf("%d", &index);
                getchar();
                modifyPoem(index);
                break;
            case 5:
                locsolas();
                break;
            case 0:
                printf("Viszlát!\n");
                break;
            default:
                printf("Érvénytelen választás.\n");
                break;
        }
    } while (choice != 0);

    return 0;
}