import pokemon from 'pokemontcgsdk';

pokemon.configure({apiKey: 'e030d28b-a210-4552-bec5-3b63be6b970b'});
pokemon.card.find('base1-4')
    .then(card => {
        console.log(card.name) // "Charizard"
    });