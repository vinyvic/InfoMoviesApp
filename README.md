# Info Movies App
Info Movies App is an Android application that allows the user to view information about a chosen movie after selecting a genre.
<p align="center">
	<img src="https://raw.githubusercontent.com/Cardos0/InfoMoviesApp/master/app/src/main/res/drawable/logo.jpeg" alt="Info Movies APP Logo">
</p>

#### Professor Rodrigo Bossini
#### https://sites.google.com/site/professorrodrigobossini/
#### Fatec Carapicuíba
#### Tecnologia em Análise e Desenvolvimento de Sistemas
#### Professor Rodrigo Bossini
#### Programação para Dispositivos Móveis
#### Projeto de Desenvolvimento de Software

## Instruções
1. O projeto pode ser desenvolvido por grupos de 1 a 4 alunos.
2. O grupo deverá criar uma conta no github para controlar o desenvolvimento do projeto.
3. O grupo deverá acessar o site www.rawshorts.com ou www.animaker.co para produzir
um vídeo ilustrativo da aplicação. A ideia é que o vídeo mostre para o usuário final, de
maneira simples, as funcionalidades da aplicação.
4. O grupo deverá exibir o aplicativo funcionando detalhando cada funcionalidade
implementada. Também deverá ser apresentado o vídeo promocional do aplicativo. Ao
final da apresentação, o professor irá realizar perguntas para cada integrante, inclusive
pedindo explicações sobre trechos de código arbitrários.
5. A apresentação poderá ser feita em um emulador ou dispositivo real. Quando possível,
é recomendável utilizar um dispositivo real e espelhar sua tela com o software Vysor. O
grupo pode levar computador próprio ou utilizar um notebook da Fatec no dia. É
exclusiva responsabilidade do grupo testar com antecedência um computador do
laboratório caso vá utilizá-lo. Explicações como “funciona na minha máquina” não são
aceitas. Caso o aplicativo não funcione, a nota será zero.
6. A nota coletiva pela apresentação é de até 2 pontos, dependendo da qualidade e
quantidade das funcionalidades apresentadas. Haverá uma quantidade de perguntas para
cada membro do grupo, a partir das quais um ponto poderá ser atribuído, totalizando
quatro.
7. O grupo deve criar um conta no site https://www.themoviedb.org/ para acessar a base
de filmes. Uma vez criada a conta, o grupo terá acesso a uma chave para a API V3. É
necessário utilizar essa chave. Estude a documentação e aprenda a fazer. Entre no link
https://developers.themoviedb.org/3 para verificar os serviços disponíveis. O papel de um
desenvolvedor profissional envolve, entre outras coisas, estudar e dominar os serviços que
pretende utilizar.
8. A apresentação ocorrerá no dia 08/12/2018, a partir das 12h10.
Professor Rodrigo Bossini
https://sites.google.com/site/professorrodrigobossini/
9. Até a meia noite do dia 11/12/2018, o grupo deverá enviar por e-mail
(profbosssini.fateccarapicuiba@gmail.com) o seguinte:
- Assunto: Projeto TMDB Dispositivos Móveis (Manhã ou Noite)
- Corpo do e-mail: Link do repositório no github + nome completo (sem abreviações de
cada integrante) + RA de cada integrante + link no youtube com o vídeo. Caso esse email
não seja enviado a nota do projeto será zero, incluindo a apresentação.
O projeto consiste no desenvolvimento de uma aplicação para dispositivos móveis com
Android. Ele deve ser desenvolvido utilizando-se o IDE Android Studio. O aplicativo
permitirá a consulta de filmes por gêneros. Veja um mockup no final desse documento.

## Funcionalidades requeridas
I. Ao acessar a aplicação, o usuário vê uma lista de gêneros de filmes. A lista de gêneros pode ser obtida pelo link https://developers.themoviedb.org/3/genres/get-movie-list.

II. Uma vez selecionado um gênero, a aplicação mostra uma lista de até 50 filmes com
aquele gênero. A lista de filmes por gênero pode ser obtida em https://developers.themoviedb.org/3/discover/movie-discover. Preste atenção no parâmetro with_genres.

III. Uma vez selecionado um filme na lista, a aplicação mostra uma foto e descrição
sucinta sobre ele. Os detalhes de um filme podem ser obtidos em https://developers.themoviedb.org/3/movies/get-movie-details.

## Requerimentos adicionais
I. O grupo obrigatoriamente desenvolverá um vídeo promocional do aplicativo e o publicará no Youtube.

II. A aplicação deve ter suporte à internacionalização, incluindo os idiomas inglês, português do brasil, espanhol, alemão e japonês

III. O inglês deve ser o idioma padrão.

IV. Preocupe-se com a acessibilidade. Adicione um texto descritivo a cada componente visual da aplicação que será reproduzido caso o usuário ative o Talkback.

V. A aplicação deve utilizar uma combinação de cores apropriada do Material Design. A cor primária deve ser um tom de laranja. Escolha as demais de modo que “combinem”, ou seja, de acordo com as recomendações Material Design. Além de o Android Studio
mostrar sugestões, também é possível visualizar as cores que “combinam” no link a seguir: https://material.io/guidelines/style/color.html#color-color-palette
