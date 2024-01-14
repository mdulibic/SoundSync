package fer.drumre.soundsync.ui.home.model

import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState

data class HomeUiState(
    val tagsToExploreUiState: TagsToExploreUiState,
    val favouritesUiState: FavouritesUiState,
)
