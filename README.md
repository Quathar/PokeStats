<style>
    .center { text-align: center }

    #title { font-size: 2.5rem }

    .grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-gap: 2rem;
        padding: 2rem;
    }
</style>

# <div id="title" class="center">📱 Mobile Application</div>
## <div class="center">🌌 _[Quathar][Q] & [Rakioth][R]_ 💊</div>

[Q]: https://github.com/Quathar
[R]: https://github.com/Rakioth
---
This is an Android Mobile Application of PokemonTCG (Trading Card Game).

In this application you can search for the sets and their respective cards available in the [pokemontcg.io](https://pokemontcg.io/) API.

API manipulation is done via [Retrofit](https://square.github.io/retrofit/).

# <div class="center">🌆 Views</div>

The application contains 3 different views.
In the sets view you can search the sets of cards you are interested in, once you touch one set you will be in the cards view, where you can see all the cards of that set and finally, if you touch a card, you will see the card graphics and a brief information about it.

All the app views are shown below with light and dark themes.

## <div class="center">🗃️ Sets View</div>
<div class="grid">
    <img src="img/sets.jpg">
    <img src="img/sets_dark.jpg">
</div>

## <div class="center">🃏 Cards View</div>
<div class="grid">
    <img src="img/cards.jpg">
    <img src="img/cards_dark.jpg">
</div>

## <div class="center">📖 Card Details View</div>
<div class="grid">
    <img src="img/cardImage.jpg">
    <img src="img/cardDetails.jpg">
    <img src="img/cardImage_dark.jpg">
    <img src="img/cardDetails_dark.jpg">
</div>