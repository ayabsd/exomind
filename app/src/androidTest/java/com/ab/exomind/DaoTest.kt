package com.ab.exomind

/**
 * Created by Aya Boussaadia on 23,February,2021
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ab.exomind.local.AppDatabase
import com.ab.exomind.local.UserDAO
import com.ab.exomind.model.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Aya Boussaadia on 21,December,2020
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: AppDatabase
    private lateinit var dao: UserDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.postDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPhotoItem() = runBlockingTest {
        val photosItem1 = User(
            "1",
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            "1-770-736-8031 x56442",
            "hildegard.org")

        dao.insertAllUsers(photosItem1)
        val allItems = dao.allUsers
        assertThat(allItems).contains(photosItem1)
    }




}
