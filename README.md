# computerprestaties

- Plaatsten scripts
- Dockerfile definitie
- Hoe werkt dit? Handige commando's met uitleg

## Docker installeren

Docker downloaden: https://www.docker.com/products/docker-desktop.

Docker Installeren voor Windows of Mac.

Werken vanuit Terminal (Mac) of Terminal (Windows, PowerShell) 

## Bouwen van de container

```bash
cd /path/to/git/computerprestaties
docker build . -f docker/Dockerfile -t computerprestaties
```

## Draaien van de container
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash
```

Hierna krijg je een shell prompt van de ubuntu container (je eigen linux server). Je wordt afgeleverd in de `/opt/scripts` directory en kan de scripts gaan draaien met de onderstaande commando's
Het ziet er als volgt uit:
```bash
root@10b9a46c0255:/opt/scripts# 
```

Je kan met de resources spelen om te kijken naar de effecten.

## Draaien van het java script
```bash
java -cp . Matrix.java
```

## Draaien van het python script
```bash
python3.8 matrix.py
```

## Draaien van het C script
```bash
gcc -O3 -o matrix matrix.c && ./matrix
```

Probeer ook eens geen optimalisatie toe te passen en kijk naar het resultaat.

Bijvoorbeeld:
```bash
gcc -o matrix matrix.c && ./matrix
```

### Tips
Wil je niet in de linux container hoeven rond te snuffelen? Gebruik dan:
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash -c 'java -cp . Matrix.java'
```

Vervang de qoutes met wat je wil

