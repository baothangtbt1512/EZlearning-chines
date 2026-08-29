package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.LearnerRepository
import com.example.data.World1Curriculum
import com.example.data.World2Curriculum
import com.example.data.World3Curriculum
import com.example.data.World4Curriculum
import com.example.data.World5Curriculum
import com.example.model.SkillType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Learning Chinese", appName)
  }

  @Test
  fun `verify world 1 curriculum 9 nodes exist with micro lessons`() {
    val courses = World1Curriculum.world1NodeCourses
    assertEquals(9, courses.size)

    val node1 = World1Curriculum.getNodeCourse("w1_n1")
    assertNotNull(node1)
    assertEquals("Home Base", node1?.title)
    assertEquals(9, node1?.microLessons?.size)

    val node3ToneGame = World1Curriculum.getNodeCourse("w1_n3")
    assertNotNull(node3ToneGame)
    assertEquals(8, node3ToneGame?.microLessons?.size)

    val node9Boss = World1Curriculum.getNodeCourse("w1_n9")
    assertNotNull(node9Boss)
    assertEquals(1, node9Boss?.microLessons?.size)
    assertEquals(8, node9Boss?.microLessons?.first()?.activities?.size)
  }

  @Test
  fun `verify world 4 and world 5 curriculum 9 nodes exist each`() {
    val coursesW4 = World4Curriculum.world4NodeCourses
    assertEquals(9, coursesW4.size)
    val w4Node1 = World4Curriculum.getNodeCourse("w4_n1")
    assertNotNull(w4Node1)
    assertEquals("School", w4Node1?.title)
    val w4Boss = World4Curriculum.getNodeCourse("w4_n9")
    assertNotNull(w4Boss)
    assertEquals(2, w4Boss?.microLessons?.size)

    val coursesW5 = World5Curriculum.world5NodeCourses
    assertEquals(9, coursesW5.size)
    val w5Node1 = World5Curriculum.getNodeCourse("w5_n1")
    assertNotNull(w5Node1)
    assertEquals("Beijing Market", w5Node1?.title)
    val w5Boss = World5Curriculum.getNodeCourse("w5_n9")
    assertNotNull(w5Boss)
    assertEquals(2, w5Boss?.microLessons?.size)
  }

  @Test
  fun `verify learner repository evidence and lesson completion`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val repository = LearnerRepository(context)

    // Complete micro-lesson
    repository.completeMicroLesson("w1_n1", "w1_n1_l1", 10)
    val state = repository.state.value
    assertTrue(state.xp >= 10)

    // Record evidence
    repository.recordEvidence(
      worldId = "world_1",
      nodeId = "w1_n1",
      lessonId = "w1_n1_l1",
      activityId = "w1_n1_l1_a1",
      itemId = "w1_hello",
      skill = SkillType.VOCABULARY,
      correct = true,
      score = 1.0
    )

    val updatedState = repository.state.value
    assertTrue((updatedState.skills[SkillType.VOCABULARY] ?: 0) > 0)
  }

  @Test
  fun `verify lesson review data extraction contains hanzi pinyin and vietnamese`() {
    val nodeCourse = World1Curriculum.getNodeCourse("w1_n1")
    assertNotNull(nodeCourse)
    val firstLesson = nodeCourse!!.microLessons.first()
    val (reviewItems, explanation) = com.example.ui.components.extractLessonReviewData(firstLesson, nodeCourse)

    assertTrue(reviewItems.isNotEmpty())
    assertTrue(explanation.isNotBlank())
    val item = reviewItems.first()
    assertTrue(item.hanzi.isNotBlank())
    assertTrue(item.pinyin.isNotBlank())
    assertTrue(item.vietnameseMeaning.isNotBlank())
  }
}
