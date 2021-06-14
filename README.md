# computerprestaties
- Wat staat er in de GitHub Computerprestaties
- Docker installeren op de desktop
- Terminal installeren, alleen voor Windows
- Opbouwen van de container (Standaard Testomgeving)
- Starten van de container en testen
    - Commando's om de verschillende talen te testen 
- Direct draaien van de testen (je hoeft niet in de container te werken) 
    - Commando's om de verschillende talen te testen 
    
## Wat staat er in de GitHub Computerprestaties
-Map Docker met de dockerfile voor de Standaard Testomgeving
-Map Scripts met de volgende programma's
(Matrixberekening met n=1024)
  matrix.c
  matrix.java
  matrix.py
-README.md
    
## Docker installeren
-Docker downloaden: https://www.docker.com/products/docker-desktop.
-Docker Installeren voor Windows of Mac.
-Werken vanuit Terminal (Mac) of Terminal (Windows, PowerShell) 
 Voor Windows Update naar WSL2 gebruik de aanwijzingen op de site: https://docs.microsoft.com/en-us/windows/wsl/install-win10

## Opbouwen van de container (Standaard Testomgeving)
De Dockerfile is om de container te bouwen. Zorg dat je pad in de terminal wijst naar de subdirectorie computerprestaties. Het commando dat begint met: docker build.... bouwt de container op

```bash
cd /path/to/git/computerprestaties
docker build . -f docker/Dockerfile -t computerprestaties
```

## Starten van de container
Je zet de container (De Standaard testomgeving) aan met het volgende commando
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash
```

Hierna krijg je een shell prompt van de ubuntu container (je eigen linux server). Je wordt afgeleverd in de `/opt/scripts` directory en kan de scripts gaan draaien met de onderstaande commando's
Het ziet er als volgt uit:
```bash
root@10b9a46c0255:/opt/scripts# 
```

### Testen van het java script met n=1024
```bash
java -cp . matrix.java
```

### Testen van het python script met n=1024
```bash
python3.8 matrix.py
```

### Testen van het C script met n=1024
```bash
gcc -O3 -o matrix matrix.c && ./matrix
```

### Testen van het java script met n=2048
Verander in de programma's de waarde van N in 2048. Let op de testen duren echt langer.


Je kan met de resources spelen om te kijken naar de effecten. Die Resources vind je in Docker->Preferences->Resources

Bij C is de optimalisatie level 3 gebruikt. Probeer ook eens geen optimalisatie of level 1 en 2 optimalisatie toe te passen en kijk naar het resultaat.

Geen Optimalisatie:
```bash
gcc -o matrix matrix1024.c && ./matrix
```
Optimalisatie level 1:
```bash
gcc -O1 -o matrix matrix1024.c && ./matrix
```
Optimalisatie level 2:
```bash
gcc -O2 -o matrix matrix1024.c && ./matrix
```

## Direct draaien van de testen (je hoeft niet in de container te werken)
Wil je niet in de linux container hoeven rond te snuffelen? Gebruik dan:
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash -c 'java -cp . matrix1024.java'
```

Vervang de inhoud tussen de 'qoutes' met ander varianten van de testen

## Werken met OpenCilk (c multi-threading)

- Download the prefab container (https://github.com/OpenCilk/opencilk-project/releases/download/opencilk%2Fv1.0/docker-opencilk-v1.0.tar.gz)
- `docker load < /path/to/docker-opencilk-v1.0.tar.gz`

en draai:

```bash
clang -o matrix-multithread -fopencilk -O3 matrix.c && ./matrix-multithread
```

