package silveira.avila.com.gamematcher2.di.module.dao

import dagger.Module
import dagger.Provides
import silveira.avila.com.gamematcher2.db.MatchDatabase
import silveira.avila.com.gamematcher2.db.dao.PlayerDao
import silveira.avila.com.gamematcher2.di.module.viewmodel.MatchViewModelModule

@Module(includes = [MatchViewModelModule::class])
class PlayerModule {


    @Provides
    fun provideTaskDao(db : MatchDatabase) : PlayerDao{
        return db.playerDao()
    }

}