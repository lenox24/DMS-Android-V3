package dsm.android.v3.ui.musicApply

interface MusicApplyNavigator{
    fun setViewPager(mondayCount: Int, tuesdayCount: Int, wednesdayCount: Int, thursdayCount: Int, fridayCount: Int)
    fun intentMusicApplyLog()
}