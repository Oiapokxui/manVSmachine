# Labirinto do Queijo

Este programa tem como intuito completar um (aparentemente simples) desafio: Fazer com que um ratinho chegue o mais rápido
no seu tão esperado queijo, percorrendo um labirinto todinho! Entretanto, essa não é uma tarefa trivial para um mero computador...

## O desafio
- Começamos com uma matriz `L` (Nosso `Labirinto`), originalmente com todos elementos preenchidos por `0` ou `-1`. 
Se um elemento vale `0`, significa que é uma **passagem livre** ; Se vale `-1`, significa que é uma **parede**.

- Dentro de nosso Labirinto, escolhemos uma **passagem livre** para colocar o **queijo**. A casa que abrigá-lo receberá o valor `1`.
Em seguida, todas as casas cuja passagem é livre são preenchidas com um número `K > 1`, de acordo com a distância `K-1` da casa ao queijo. Assim, as **casas
livres adjacentes ao queijo** recebem o valor `K = 2`, e as livres adjacentes a essas recebem o valor `K = 3`, e assim por diante,
até que todas as casas estejam prenchidas.
(Vale lembrar que apenas as casas que possuem um caminho livre para queijo serão preenchidas.)

- Por fim, a partir da posição do **rato**, se tal caminho existe, ele se locomove sempre para a casa cujo valor é **menor que** o atual valor
da casa onde se encontra, marcando todas as casas por onde ele anda. Assim, ele percorre apenas o caminho mais curto sempre!

## Como implementei
- WIP

## Quem propôs
O desafio é proposto na seção de exercícios complementares do [material didático](https://www.ime.usp.br/~macmulti/exercicios/extra/index.html)
da disciplina Introdução à Computação, ministrada no Instituto de Matemática e Estatística da USP.
