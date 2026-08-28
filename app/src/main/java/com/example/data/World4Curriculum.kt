package com.example.data

import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.model.PandaEmotion
import com.example.model.Quest
import com.example.model.SkillType
import com.example.model.interpolateLearner

object World4Curriculum {

  // -------------------------------------------------------------
  // ALL WORLD 4 LEARNING ITEMS
  // -------------------------------------------------------------
  val items = listOf(
    // Node 1: School Environment
    LearningItem("w4_xuexiao", "学校", "xuéxiào", "Trường học", usageNote = "Môi trường học tập và rèn luyện.", exampleSentence = "这是我的学校。", examplePinyin = "zhè shì wǒ de xuéxiào.", exampleTranslation = "Đây là trường học của tôi.", category = "school"),
    LearningItem("w4_daxue", "大学", "dàxué", "Trường đại học", usageNote = "Bậc học đại học, cao đẳng.", exampleSentence = "我在大学学习。", examplePinyin = "wǒ zài dàxué xuéxí.", exampleTranslation = "Tôi học ở trường đại học.", category = "school"),
    LearningItem("w4_jiaoshi", "教室", "jiàoshì", "Phòng học / Lớp học", usageNote = "Căn phòng nơi diễn ra giờ học.", exampleSentence = "这是教室。", examplePinyin = "zhè shì jiàoshì.", exampleTranslation = "Đây là phòng học.", category = "school"),
    LearningItem("w4_ke", "课", "kè", "Bài học / Tiết học / Lớp học", usageNote = "Tiết học hoặc môn học.", exampleSentence = "我们上中文课。", examplePinyin = "wǒmen shàng Zhōngwén kè.", exampleTranslation = "Chúng tôi học tiết tiếng Trung.", category = "school"),
    LearningItem("w4_shu", "书", "shū", "Sách / Sách giáo khoa", usageNote = "Tài liệu và sách vở học tập.", exampleSentence = "这是中文书。", examplePinyin = "zhè shì Zhōngwén shū.", exampleTranslation = "Đây là sách tiếng Trung.", category = "school"),
    LearningItem("w4_zhuozi", "桌子", "zhuōzi", "Cái bàn / Bàn học", usageNote = "Bàn học trong lớp.", exampleSentence = "书在桌子上。", examplePinyin = "shū zài zhuōzi shàng.", exampleTranslation = "Sách ở trên bàn.", category = "school"),
    LearningItem("w4_yizi", "椅子", "yǐzi", "Cái ghế", usageNote = "Ghế ngồi học.", exampleSentence = "椅子在教室里。", examplePinyin = "yǐzi zài jiàoshì lǐ.", exampleTranslation = "Ghế ở trong phòng học.", category = "school"),
    LearningItem("w4_xuesheng", "学生", "xuésheng", "Học sinh / Sinh viên", usageNote = "Người theo học.", exampleSentence = "我是学生。", examplePinyin = "wǒ shì xuésheng.", exampleTranslation = "Tôi là học sinh.", category = "people"),
    LearningItem("w4_laoshi", "老师", "lǎoshī", "Thầy cô giáo", usageNote = "Người truyền đạt kiến thức.", exampleSentence = "老师好！", examplePinyin = "lǎoshī hǎo!", exampleTranslation = "Em chào thầy cô!", category = "people"),

    // Node 2: Classmates & Friends
    LearningItem("w4_tongxue", "同学", "tóngxué", "Bạn cùng lớp / Bạn học", usageNote = "Người bạn học cùng lớp.", exampleSentence = "他是我的同学。", examplePinyin = "tā shì wǒ de tóngxué.", exampleTranslation = "Anh ấy là bạn cùng lớp của tôi.", category = "people"),
    LearningItem("w4_pengyou", "朋友", "péngyou", "Bạn bè / Bạn thân", usageNote = "Người bạn tri kỷ đồng hành.", exampleSentence = "她是我的朋友。", examplePinyin = "tā shì wǒ de péngyou.", exampleTranslation = "Cô ấy là bạn của tôi.", category = "people"),
    LearningItem("w4_nan_tongxue", "男同学", "nán tóngxué", "Bạn học nam", usageNote = "Bạn nam cùng lớp.", exampleSentence = "他是男同学。", examplePinyin = "tā shì nán tóngxué.", exampleTranslation = "Cậu ấy là bạn học nam.", category = "people"),
    LearningItem("w4_nv_tongxue", "女同学", "nǚ tóngxué", "Bạn học nữ", usageNote = "Bạn nữ cùng lớp.", exampleSentence = "她是女同学。", examplePinyin = "tā shì nǚ tóngxué.", exampleTranslation = "Cô ấy là bạn học nữ.", category = "people"),
    LearningItem("w4_renshi", "认识", "rènshi", "Quen biết / Nhận biết", usageNote = "Quen biết một ai đó.", exampleSentence = "我认识他。", examplePinyin = "wǒ rènshi tā.", exampleTranslation = "Tôi quen biết anh ấy.", category = "social"),
    LearningItem("w4_ye", "也", "yě", "Cũng", usageNote = "Biểu thị sự tương đồng.", exampleSentence = "我也认识她。", examplePinyin = "wǒ yě rènshi tā.", exampleTranslation = "Tôi cũng quen cô ấy.", category = "grammar"),

    // Node 3: Teachers & Students (Roles & Classroom Actions)
    LearningItem("w4_jiao", "教", "jiāo", "Dạy / Giảng dạy", usageNote = "Hành động dạy học của giáo viên.", exampleSentence = "老师教中文。", examplePinyin = "lǎoshī jiāo Zhōngwén.", exampleTranslation = "Thầy giáo dạy tiếng Trung.", category = "verb"),
    LearningItem("w4_xuexi", "学习", "xuéxí", "Học tập / Nghiên cứu", usageNote = "Hành động học tập.", exampleSentence = "我们学习中文。", examplePinyin = "wǒmen xuéxí Zhōngwén.", exampleTranslation = "Chúng tôi học tiếng Trung.", category = "verb"),
    LearningItem("w4_shangke", "上课", "shàngkè", "Vào lớp / Bắt đầu tiết học", usageNote = "Bắt đầu giờ giảng dạy.", exampleSentence = "上课了！", examplePinyin = "shàngkè le!", exampleTranslation = "Đến giờ vào lớp rồi!", category = "action"),
    LearningItem("w4_xiake", "下课", "xiàkè", "Tan lớp / Hết tiết", usageNote = "Kết thúc giờ học.", exampleSentence = "下课了！", examplePinyin = "xiàkè le!", exampleTranslation = "Tan lớp rồi!", category = "action"),
    LearningItem("w4_women", "我们", "wǒmen", "Chúng tôi / Chúng ta", usageNote = "Đại từ nhân xưng số nhiều.", exampleSentence = "我们一起学习。", examplePinyin = "wǒmen yìqǐ xuéxí.", exampleTranslation = "Chúng tôi cùng nhau học tập.", category = "pronoun"),

    // Node 4: I Study Chinese (4 Core Language Skills)
    LearningItem("w4_zhongwen", "中文", "Zhōngwén", "Tiếng Trung (Trung văn)", usageNote = "Ngôn ngữ và chữ viết tiếng Trung.", exampleSentence = "我学习中文。", examplePinyin = "wǒ xuéxí Zhōngwén.", exampleTranslation = "Tôi học tiếng Trung.", category = "language"),
    LearningItem("w4_hanyu", "汉语", "Hànyǔ", "Hán ngữ / Tiếng Hán", usageNote = "Tiếng Hán khẩu ngữ.", exampleSentence = "我说汉语。", examplePinyin = "wǒ shuō Hànyǔ.", exampleTranslation = "Tôi nói tiếng Hán.", category = "language"),
    LearningItem("w4_shuo", "说", "shuō", "Nói", usageNote = "Kỹ năng nói (Speaking).", exampleSentence = "我说中文。", examplePinyin = "wǒ shuō Zhōngwén.", exampleTranslation = "Tôi nói tiếng Trung.", category = "skill"),
    LearningItem("w4_ting", "听", "tīng", "Nghe", usageNote = "Kỹ năng nghe (Listening).", exampleSentence = "我听中文。", examplePinyin = "wǒ tīng Zhōngwén.", exampleTranslation = "Tôi nghe tiếng Trung.", category = "skill"),
    LearningItem("w4_du", "读", "dú", "Đọc", usageNote = "Kỹ năng đọc (Reading).", exampleSentence = "我读中文。", examplePinyin = "wǒ dú Zhōngwén.", exampleTranslation = "Tôi đọc tiếng Trung.", category = "skill"),
    LearningItem("w4_xie", "写", "xiě", "Viết", usageNote = "Kỹ năng viết (Writing).", exampleSentence = "我写汉字。", examplePinyin = "wǒ xiě hànzì.", exampleTranslation = "Tôi viết chữ Hán.", category = "skill"),
    LearningItem("w4_hanzi", "汉字", "hànzì", "Chữ Hán", usageNote = "Ký tự chữ tượng hình tiếng Trung.", exampleSentence = "汉字很有意思。", examplePinyin = "hànzì hěn yǒu yìsi.", exampleTranslation = "Chữ Hán rất thú vị.", category = "language"),

    // Node 5: What Subject Do You Like?
    LearningItem("w4_zhongwenke", "中文课", "Zhōngwén kè", "Môn Tiếng Trung", usageNote = "Môn học tiếng Trung.", exampleSentence = "我喜欢中文课。", examplePinyin = "wǒ xǐhuan Zhōngwén kè.", exampleTranslation = "Tôi thích môn tiếng Trung.", category = "subject"),
    LearningItem("w4_yingyuke", "英语课", "Yīngyǔ kè", "Môn Tiếng Anh", usageNote = "Môn học tiếng Anh.", exampleSentence = "明天有英语课。", examplePinyin = "míngtiān yǒu Yīngyǔ kè.", exampleTranslation = "Ngày mai có môn tiếng Anh.", category = "subject"),
    LearningItem("w4_shuxueke", "数学课", "shùxué kè", "Môn Toán học", usageNote = "Môn học toán số.", exampleSentence = "数学课很有意思。", examplePinyin = "shùxué kè hěn yǒu yìsi.", exampleTranslation = "Môn toán rất thú vị.", category = "subject"),
    LearningItem("w4_zui", "最", "zuì", "Nhất (Most)", usageNote = "Phó từ chỉ mức độ cao nhất.", exampleSentence = "我最喜欢中文课。", examplePinyin = "wǒ zuì xǐhuan Zhōngwén kè.", exampleTranslation = "Tôi thích môn tiếng Trung nhất.", category = "grammar"),
    LearningItem("w4_youyisi", "有意思", "yǒu yìsi", "Thú vị / Hay ho", usageNote = "Có ý nghĩa, hấp dẫn.", exampleSentence = "中文课很有意思。", examplePinyin = "Zhōngwén kè hěn yǒu yìsi.", exampleTranslation = "Môn tiếng Trung rất thú vị.", category = "adjective"),

    // Node 6: Study Together (Social Invitations)
    LearningItem("w4_yiqi", "一起", "yìqǐ", "Cùng nhau (Together)", usageNote = "Làm việc cùng với nhau.", exampleSentence = "我们一起学习。", examplePinyin = "wǒmen yìqǐ xuéxí.", exampleTranslation = "Chúng ta cùng nhau học tập nhé.", category = "social"),
    LearningItem("w4_qu", "去", "qù", "Đi (Go)", usageNote = "Di chuyển đến một địa điểm.", exampleSentence = "我们去教室。", examplePinyin = "wǒmen qù jiàoshì.", exampleTranslation = "Chúng ta đi đến phòng học nào.", category = "verb"),
    LearningItem("w4_lai", "来", "lái", "Đến / Tới (Come)", usageNote = "Đến nơi người nói đang đứng.", exampleSentence = "你可以来吗？", examplePinyin = "nǐ kěyǐ lái ma?", exampleTranslation = "Bạn có thể đến không?", category = "verb"),
    LearningItem("w4_xianzai", "现在", "xiànzài", "Bây giờ / Hiện tại", usageNote = "Thời điểm hiện tại.", exampleSentence = "现在可以吗？", examplePinyin = "xiànzài kěyǐ ma?", exampleTranslation = "Bây giờ có tiện không?", category = "time"),
    LearningItem("w4_keyi", "可以", "kěyǐ", "Có thể / Được (Can/May)", usageNote = "Biểu thị sự đồng ý hoặc khả năng.", exampleSentence = "可以，我们走吧！", examplePinyin = "kěyǐ, wǒmen zǒu ba!", exampleTranslation = "Được chứ, chúng ta đi thôi!", category = "modal"),
    LearningItem("w4_ba", "吧", "ba", "Nhé / Đi / Nào (Trợ từ ngữ khí)", usageNote = "Đặt cuối câu đề nghị, rủ rê.", exampleSentence = "一起学习吧！", examplePinyin = "yìqǐ xuéxí ba!", exampleTranslation = "Cùng nhau học bài nhé!", category = "particle"),

    // Node 7: Where Are You? (School Locations & Map)
    LearningItem("w4_zainar", "在哪儿", "zài nǎr", "Ở đâu (Where)", usageNote = "Hỏi vị trí theo ngữ điệu phương Bắc / Bắc Kinh.", exampleSentence = "你在哪儿？", examplePinyin = "nǐ zài nǎr?", exampleTranslation = "Bạn đang ở đâu thế?", category = "question"),
    LearningItem("w4_tushuguan", "图书馆", "túshūguǎn", "Thư viện", usageNote = "Nơi đọc sách yên tĩnh tại trường.", exampleSentence = "我在图书馆。", examplePinyin = "wǒ zài túshūguǎn.", exampleTranslation = "Tôi đang ở thư viện.", category = "location"),
    LearningItem("w4_shitang", "食堂", "shítáng", "Nhà ăn / Căn tin trường", usageNote = "Nơi học sinh ăn trưa, ăn tối.", exampleSentence = "我们在食堂吃饭。", examplePinyin = "wǒmen zài shítáng chīfàn.", exampleTranslation = "Chúng tôi ăn cơm ở nhà ăn trường.", category = "location"),
    LearningItem("w4_caochang", "操场", "cāochǎng", "Sân trường / Sân thể dục", usageNote = "Sân vận động, khu vực ngoài trời.", exampleSentence = "操场很大。", examplePinyin = "cāochǎng hěn dà.", exampleTranslation = "Sân trường rất rộng.", category = "location"),
    LearningItem("w4_zheli", "这里", "zhèlǐ", "Ở đây / Chỗ này", usageNote = "Vị trí gần.", exampleSentence = "我在这里。", examplePinyin = "wǒ zài zhèlǐ.", exampleTranslation = "Tôi đang ở đây này.", category = "location"),
    LearningItem("w4_nali", "那里", "nàlǐ", "Ở kia / Đằng đó", usageNote = "Vị trí xa.", exampleSentence = "他在那里。", examplePinyin = "tā zài nàlǐ.", exampleTranslation = "Anh ấy ở đằng kia.", category = "location")
  )

  val itemMap: Map<String, LearningItem> by lazy {
    items.associateBy { it.id }
  }

  // -------------------------------------------------------------
  // NODE 1: SCHOOL (学校)
  // -------------------------------------------------------------
  private val node1Lessons = listOf(
    MicroLesson(
      id = "w4_n1_l1",
      nodeId = "w4_n1",
      title = "Khám Phá Trường Học",
      subtitle = "Học từ vựng về trường học, đại học và lớp học",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n1_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_xuexiao", "w4_daxue", "w4_jiaoshi", "w4_ke"), "Khám phá không gian trường học", audioText = "学校 大学 教室 课", correctAnswer = ""),
        LearningActivity("w4_n1_a2", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_shu", "w4_zhuozi", "w4_yizi", "w4_xuesheng", "w4_laoshi"), "Khám phá đồ dùng và con người trong trường", audioText = "书 桌子 椅子 学生 老师", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n1_l2",
      nodeId = "w4_n1",
      title = "Nghe & Nhận Diện Pinyin",
      subtitle = "Lắng nghe và phân biệt xuéxiào, jiàoshì, dàxué",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n1_a3", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_xuexiao"), "Nghe âm thanh và chọn Pinyin đúng:", audioText = "学校", options = listOf("xuéxiào", "jiàoshì", "dàxué", "xuésheng"), correctAnswer = "xuéxiào", explanation = "xuéxiào nghĩa là Trường học."),
        LearningActivity("w4_n1_a4", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_jiaoshi"), "Nghe âm thanh và chọn Pinyin đúng:", audioText = "教室", options = listOf("jiàoshì", "xuéxiào", "túshūguǎn", "shítáng"), correctAnswer = "jiàoshì", explanation = "jiàoshì nghĩa là Phòng học / Lớp học.")
      )
    ),
    MicroLesson(
      id = "w4_n1_l3",
      nodeId = "w4_n1",
      title = "Nhận Diện Chữ Hán",
      subtitle = "Nối âm thanh với mặt chữ 学校 và 教室",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n1_a5", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_xuexiao"), "Nghe phát âm và chọn chữ Hán tương ứng:", audioText = "学校", options = listOf("学校", "教室", "大学", "学生"), correctAnswer = "学校"),
        LearningActivity("w4_n1_a6", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_jiaoshi"), "Nghe phát âm và chọn chữ Hán:", audioText = "教室", options = listOf("教室", "学校", "老师", "桌子"), correctAnswer = "教室")
      )
    ),
    MicroLesson(
      id = "w4_n1_l4",
      nodeId = "w4_n1",
      title = "Ghép Câu Vị Trí Trường Học",
      subtitle = "Tạo câu: 这是我的学校 & 我在教室",
      type = "sentence_builder",
      order = 4,
      activities = listOf(
        LearningActivity("w4_n1_a7", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_xuexiao"), "Ghép câu: \"Đây là trường học của tôi.\"", sentenceWords = listOf("这是", "我的", "学校", "教室", "在"), targetSentence = "这是我的学校", correctAnswer = "这是我的学校"),
        LearningActivity("w4_n1_a8", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_jiaoshi"), "Ghép câu: \"Tôi đang ở phòng học.\"", sentenceWords = listOf("我", "在", "教室", "学校", "是"), targetSentence = "我在教室", correctAnswer = "我在教室")
      )
    ),
    MicroLesson(
      id = "w4_n1_l5",
      nodeId = "w4_n1",
      title = "Tham Quan Cùng Gấu Trúc",
      subtitle = "Gấu trúc dẫn bạn đi thăm trường học Bắc Kinh",
      type = "panda_conversation",
      order = 5,
      activities = listOf(
        LearningActivity("w4_n1_a9", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_xuexiao"), "Gấu trúc đón bạn ở cổng trường:", pandaDialogue = "欢迎来到北京！这是你的学校吗？", options = listOf("是的，这是我的学校。", "我不喜欢咖啡。", "现在三点半。"), correctAnswer = "是的，这是我的学校。", explanation = "Chào mừng bạn đến với ngôi trường mới!"),
        LearningActivity("w4_n1_a10", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w4_daxue"), "Nói câu giới thiệu:", hanziPrompt = "我在大学学习。", pinyinPrompt = "wǒ zài dàxué xuéxí.", audioText = "我在大学学习", correctAnswer = "我在大学学习"),
        LearningActivity("w4_n1_a11", ActivityType.WRITING, SkillType.WRITING, listOf("w4_xuexiao"), "Gõ chữ Hán cho \"xuéxiào\":", pinyinPrompt = "xuéxiào", correctAnswer = "学校")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 2: MY CLASSMATES (我的同学)
  // -------------------------------------------------------------
  private val node2Lessons = listOf(
    MicroLesson(
      id = "w4_n2_l1",
      nodeId = "w4_n2",
      title = "Khám Phá Bạn Học",
      subtitle = "Học từ bạn học nam, bạn học nữ và quen biết",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n2_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_tongxue", "w4_pengyou", "w4_nan_tongxue", "w4_nv_tongxue", "w4_renshi", "w4_ye"), "Khám phá danh xưng bạn học", audioText = "同学 朋友 男同学 女同学 认识 也", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n2_l2",
      nodeId = "w4_n2",
      title = "Luyện Nghe & Đọc Chữ Hán",
      subtitle = "Nghe phân biệt tóngxué, nán tóngxué, nǚ tóngxué",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n2_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_tongxue"), "Nghe và chọn Pinyin:", audioText = "同学", options = listOf("tóngxué", "péngyou", "lǎoshī", "xuésheng"), correctAnswer = "tóngxué"),
        LearningActivity("w4_n2_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_nv_tongxue"), "Nghe và chọn chữ Hán cho Bạn học nữ:", audioText = "女同学", options = listOf("女同学", "男同学", "朋友", "老师"), correctAnswer = "女同学")
      )
    ),
    MicroLesson(
      id = "w4_n2_l3",
      nodeId = "w4_n2",
      title = "Ghép Câu & Làm Quen Bạn Mới",
      subtitle = "Tạo câu: 他是我的同学 & 我也认识她",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n2_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_tongxue"), "Ghép câu: \"Anh ấy là bạn cùng lớp của tôi.\"", sentenceWords = listOf("他", "是", "我的", "同学", "认识"), targetSentence = "他是我的同学", correctAnswer = "他是我的同学"),
        LearningActivity("w4_n2_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_renshi"), "Gấu trúc chỉ vào một bạn học và hỏi:", pandaDialogue = "他是谁？你认识他吗？", options = listOf("他是我的同学，我认识他。", "现在八点。", "这是桌子。"), correctAnswer = "他是我的同学，我认识他。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 3: TEACHERS & STUDENTS (老师和学生)
  // -------------------------------------------------------------
  private val node3Lessons = listOf(
    MicroLesson(
      id = "w4_n3_l1",
      nodeId = "w4_n3",
      title = "Khám Phá Vai Trò & Hoạt Động Lớp Học",
      subtitle = "Học dạy (教), học (学习), vào lớp (上课), tan lớp (下课)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n3_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_laoshi", "w4_xuesheng", "w4_jiao", "w4_xuexi", "w4_shangke", "w4_xiake", "w4_women"), "Khám phá từ vựng hoạt động lớp học", audioText = "老师 学生 教 学习 上课 下课 我们", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n3_l2",
      nodeId = "w4_n3",
      title = "Luyện Nghe Hoạt Động Lớp",
      subtitle = "Nghe và nhận diện câu thông báo vào lớp / tan lớp",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n3_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_shangke"), "Nghe hiệu lệnh lớp học:", audioText = "上课了", options = listOf("shàngkè le (Vào lớp)", "xiàkè le (Tan lớp)", "qǐchuáng le", "chīfàn le"), correctAnswer = "shàngkè le (Vào lớp)"),
        LearningActivity("w4_n3_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_jiao"), "Nghe và chọn câu chữ Hán:", audioText = "老师教中文", options = listOf("老师教中文", "我们学习中文", "上课了", "这是教室"), correctAnswer = "老师教中文")
      )
    ),
    MicroLesson(
      id = "w4_n3_l3",
      nodeId = "w4_n3",
      title = "Ghép Câu & Hội Thoại Thầy Trò",
      subtitle = "Tạo câu: 老师教中文，我们学习中文",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n3_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_women"), "Ghép câu: \"Chúng tôi học tiếng Trung.\"", sentenceWords = listOf("我们", "学习", "中文", "老师", "教"), targetSentence = "我们学习中文", correctAnswer = "Chúng tôi học tiếng Trung"),
        LearningActivity("w4_n3_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_shangke"), "Chuông reo, Gấu trúc nhắc bạn:", pandaDialogue = "铃声响了，上课了吗？", options = listOf("上课了，我们去教室吧！", "我叫 {{learner.name}}。", "我家有四个人。"), correctAnswer = "上课了，我们去教室吧！")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 4: I STUDY CHINESE (我学习中文)
  // -------------------------------------------------------------
  private val node4Lessons = listOf(
    MicroLesson(
      id = "w4_n4_l1",
      nodeId = "w4_n4",
      title = "Khám Phá 4 Kỹ Năng Ngôn Ngữ",
      subtitle = "Nghe (听), Nói (说), Đọc (读), Viết (写) và Chữ Hán (汉字)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n4_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_zhongwen", "w4_hanyu", "w4_shuo", "w4_ting", "w4_du", "w4_xie", "w4_hanzi"), "Khám phá 4 kỹ năng ngôn ngữ", audioText = "中文 汉语 说 听 读 写 汉字", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n4_l2",
      nodeId = "w4_n4",
      title = "Luyện Nghe Kỹ Năng",
      subtitle = "Phân biệt phát âm shuō, tīng, dú, xiě",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n4_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_shuo"), "Nghe và chọn Pinyin cho \"Nói\":", audioText = "说", options = listOf("shuō", "tīng", "dú", "xiě"), correctAnswer = "shuō"),
        LearningActivity("w4_n4_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_xie"), "Nghe và chọn chữ Hán cho \"Viết\":", audioText = "写", options = listOf("写", "读", "说", "听"), correctAnswer = "写")
      )
    ),
    MicroLesson(
      id = "w4_n4_l3",
      nodeId = "w4_n4",
      title = "Ghép Câu & Luyện 4 Kỹ Năng",
      subtitle = "Tạo câu: 我说中文 & 我写汉字",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n4_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_shuo"), "Ghép câu: \"Tôi nói tiếng Trung.\"", sentenceWords = listOf("我", "说", "中文", "写", "汉字"), targetSentence = "我说中文", correctAnswer = "我说中文"),
        LearningActivity("w4_n4_a5", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_xie"), "Ghép câu: \"Tôi viết chữ Hán.\"", sentenceWords = listOf("我", "写", "汉字", "说", "汉语"), targetSentence = "我写汉字", correctAnswer = "我写汉字"),
        LearningActivity("w4_n4_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_hanyu"), "Gấu trúc hỏi bạn:", pandaDialogue = "你会说汉语吗？", options = listOf("我会说一点儿汉语。", "我是医生。", "现在八点。"), correctAnswer = "我会说一点儿汉语。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 5: WHAT SUBJECT DO YOU LIKE? (你喜欢什么课？)
  // -------------------------------------------------------------
  private val node5Lessons = listOf(
    MicroLesson(
      id = "w4_n5_l1",
      nodeId = "w4_n5",
      title = "Khám Phá Các Môn Học",
      subtitle = "Môn tiếng Trung, tiếng Anh, toán học và phó từ 最 (nhất)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n5_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_ke", "w4_zhongwenke", "w4_yingyuke", "w4_shuxueke", "w4_zui", "w4_youyisi"), "Khám phá các môn học yêu thích", audioText = "课 中文课 英语课 数学课 最 有意思", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n5_l2",
      nodeId = "w4_n5",
      title = "Luyện Nghe Môn Học",
      subtitle = "Nghe và nhận biết Trung văn khóa, Anh ngữ khóa",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n5_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_zhongwenke"), "Nghe và chọn Pinyin:", audioText = "中文课", options = listOf("Zhōngwén kè", "Yīngyǔ kè", "shùxué kè", "xuéxí"), correctAnswer = "Zhōngwén kè"),
        LearningActivity("w4_n5_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_youyisi"), "Nghe và chọn chữ Hán cho \"Thú vị\":", audioText = "有意思", options = listOf("有意思", "喜欢", "学校", "朋友"), correctAnswer = "有意思")
      )
    ),
    MicroLesson(
      id = "w4_n5_l3",
      nodeId = "w4_n5",
      title = "Ghép Câu & Bày Tỏ Sở Thích Học Tập",
      subtitle = "Tạo câu: 我最喜欢中文课，中文课很有意思",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n5_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_zui"), "Ghép câu: \"Tôi thích môn tiếng Trung nhất.\"", sentenceWords = listOf("我", "最", "喜欢", "中文课", "数学课"), targetSentence = "我最喜欢中文课", correctAnswer = "我最喜欢中文课"),
        LearningActivity("w4_n5_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_zhongwenke"), "Gấu trúc hỏi sở thích của bạn:", pandaDialogue = "你喜欢什么课？", options = listOf("我最喜欢中文课，很有意思！", "我是老师。", "我叫 {{learner.name}}。"), correctAnswer = "我最喜欢中文课，很有意思！")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 6: STUDY TOGETHER (一起学习)
  // -------------------------------------------------------------
  private val node6Lessons = listOf(
    MicroLesson(
      id = "w4_n6_l1",
      nodeId = "w4_n6",
      title = "Khám Phá Lời Mời & Hẹn Gặp",
      subtitle = "Học cùng nhau (一起), đi (去), đến (来), có thể (可以), trợ từ 吧",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n6_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_yiqi", "w4_xuexi", "w4_qu", "w4_lai", "w4_xianzai", "w4_keyi", "w4_ba"), "Khám phá lời rủ rê & giao tiếp", audioText = "一起 学习 去 来 现在 可以 吧", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n6_l2",
      nodeId = "w4_n6",
      title = "Luyện Nghe Lời Mời Cùng Học",
      subtitle = "Nghe câu: 我们一起学习吧！",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n6_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_yiqi"), "Nghe và chọn Pinyin của \"Cùng nhau\":", audioText = "一起", options = listOf("yìqǐ", "xiànzài", "kěyǐ", "xuéxí"), correctAnswer = "yìqǐ"),
        LearningActivity("w4_n6_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_keyi"), "Nghe và chọn chữ Hán cho \"Có thể\":", audioText = "可以", options = listOf("可以", "一起", "现在", "喜欢"), correctAnswer = "可以")
      )
    ),
    MicroLesson(
      id = "w4_n6_l3",
      nodeId = "w4_n6",
      title = "Ghép Câu & Hội Thoại Rủ Rê Phân Nhánh",
      subtitle = "Tạo câu rủ bạn cùng học và phản hồi tự nhiên",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n6_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_yiqi"), "Ghép câu: \"Chúng ta cùng nhau học tập nhé.\"", sentenceWords = listOf("我们", "一起", "学习", "吧", "去"), targetSentence = "我们一起学习吧", correctAnswer = "我们一起学习吧"),
        LearningActivity("w4_n6_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_keyi"), "Gấu trúc rủ bạn:", pandaDialogue = "现在可以一起学习吗？", options = listOf("好啊，我们去教室！", "太贵了。", "他是我爸爸。"), correctAnswer = "好啊，我们去教室！")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 7: WHERE ARE YOU? (你在哪儿？)
  // -------------------------------------------------------------
  private val node7Lessons = listOf(
    MicroLesson(
      id = "w4_n7_l1",
      nodeId = "w4_n7",
      title = "Khám Phá Sơ Đồ Địa Điểm Trường Học",
      subtitle = "Thư viện (图书馆), nhà ăn (食堂), sân trường (操场), ở đâu (在哪儿)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n7_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w4_zainar", "w4_tushuguan", "w4_shitang", "w4_caochang", "w4_zheli", "w4_nali"), "Khám phá các khu vực trong trường", audioText = "在哪儿 图书馆 食堂 操场 这里 那里", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w4_n7_l2",
      nodeId = "w4_n7",
      title = "Luyện Nghe Nhận Diện Vị Trí",
      subtitle = "Nghe câu: 我在图书馆 & 我们在食堂",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n7_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w4_tushuguan"), "Nghe và chọn địa điểm đúng:", audioText = "图书馆", options = listOf("túshūguǎn (Thư viện)", "shítáng (Nhà ăn)", "cāochǎng (Sân trường)", "jiàoshì (Lớp học)"), correctAnswer = "túshūguǎn (Thư viện)"),
        LearningActivity("w4_n7_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w4_shitang"), "Nghe và chọn chữ Hán cho \"Nhà ăn\":", audioText = "食堂", options = listOf("食堂", "图书馆", "操场", "学校"), correctAnswer = "食堂")
      )
    ),
    MicroLesson(
      id = "w4_n7_l3",
      nodeId = "w4_n7",
      title = "Ghép Câu & Định Vị Cùng Gấu Trúc",
      subtitle = "Tạo câu: 我在图书馆 & 他在操场",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w4_n7_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_tushuguan"), "Ghép câu: \"Tôi đang ở thư viện.\"", sentenceWords = listOf("我", "在", "图书馆", "食堂", "去"), targetSentence = "我在图书馆", correctAnswer = "我在图书馆"),
        LearningActivity("w4_n7_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_zainar"), "Gấu trúc tìm bạn trong khuôn viên trường:", pandaDialogue = "你在哪儿？", options = listOf("我在图书馆看书呢。", "我喜欢喝茶。", "今天星期一。"), correctAnswer = "我在图书馆看书呢。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 8: MY SCHOOL LIFE (我的学校生活) - INTEGRATION NODE
  // -------------------------------------------------------------
  private val node8Lessons = listOf(
    MicroLesson(
      id = "w4_n8_l1",
      nodeId = "w4_n8",
      title = "Hội Thoại Tích Hợp Toàn Diện Học Đường",
      subtitle = "Kết hợp vai trò sinh viên, môn học, bạn bè và vị trí",
      type = "integration_dialogue",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n8_a1", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_daxue"), "Gấu trúc hỏi bạn:", pandaDialogue = "你也是大学生吗？", options = listOf("是的，我在大学学习中文。", "我不喜欢咖啡。", "我家有四个人。"), correctAnswer = "是的，我在大学学习中文。"),
        LearningActivity("w4_n8_a2", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_zhongwenke"), "Gấu trúc hỏi về môn học:", pandaDialogue = "你觉得中文课怎么样？", options = listOf("中文课很有意思，我最喜欢！", "现在三点半。", "这是我妹妹。"), correctAnswer = "中文课很有意思，我最喜欢！"),
        LearningActivity("w4_n8_a3", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_yiqi"), "Gấu trúc rủ bạn:", pandaDialogue = "下课后我们一起去图书馆吧？", options = listOf("好啊，我们一起去！", "再见！", "他是我老师。"), correctAnswer = "好啊，我们一起去！")
      )
    ),
    MicroLesson(
      id = "w4_n8_l2",
      nodeId = "w4_n8",
      title = "Luyện Thuyết Trình Học Đường & Viết Chữ Hán",
      subtitle = "Nói trôi chảy câu giới thiệu một ngày học tập sôi động",
      type = "speaking_writing",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n8_a4", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w4_xuexi"), "Đọc câu tự giới thiệu trường học:", hanziPrompt = "我在大学学习中文，我和同学一起去教室。", pinyinPrompt = "wǒ zài dàxué xuéxí Zhōngwén, wǒ hé tóngxué yìqǐ qù jiàoshì.", audioText = "我在大学学习中文，我和同学一起去教室", correctAnswer = "我在大学学习中文，我和同学一起去教室"),
        LearningActivity("w4_n8_a5", ActivityType.WRITING, SkillType.WRITING, listOf("w4_zhongwen"), "Gõ chữ Hán cho \"Zhōngwén\":", pinyinPrompt = "Zhōngwén", correctAnswer = "中文")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 9: SCHOOL CHALLENGE (学校挑战) - WORLD 4 BOSS
  // -------------------------------------------------------------
  private val node9Lessons = listOf(
    MicroLesson(
      id = "w4_n9_l1",
      nodeId = "w4_n9",
      title = "Đấu Trường Học Đường - Vòng 1 đến 5",
      subtitle = "Thử thách phản xạ giao tiếp trôi chảy trong môi trường trường học",
      type = "boss_challenge",
      order = 1,
      activities = listOf(
        LearningActivity("w4_n9_a1", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w4_xuesheng"), "Vòng 1: Xác nhận vai trò:", pandaDialogue = "你是谁？", options = listOf("我是学生，我在大学学习。", "这是桌子。", "今天星期五。"), correctAnswer = "我是学生，我在大学学习。", roundNumber = 1),
        LearningActivity("w4_n9_a2", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w4_zainar"), "Vòng 2: Hỏi vị trí hiện tại:", pandaDialogue = "你在哪儿？", options = listOf("我在教室上课。", "我今年十八岁。", "我喝水。"), correctAnswer = "我在教室上课。", roundNumber = 2),
        LearningActivity("w4_n9_a3", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w4_zhongwen"), "Vòng 3: Hỏi nội dung học:", pandaDialogue = "你学习什么？", options = listOf("我学习中文，我说汉语。", "我有四个朋友。", "现在八点。"), correctAnswer = "我学习中文，我说汉语。", roundNumber = 3),
        LearningActivity("w4_n9_a4", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w4_zhongwenke"), "Vòng 4: Hỏi môn học yêu thích:", pandaDialogue = "你最喜欢什么课？", options = listOf("我最喜欢中文课，很有意思！", "我不喜欢吃苹果。", "这是我爸爸。"), correctAnswer = "我最喜欢中文课，很有意思！", roundNumber = 4),
        LearningActivity("w4_n9_a5", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w4_yiqi"), "Vòng 5: Hỏi bạn học cùng:", pandaDialogue = "你和谁一起学习？", options = listOf("我和同学一起学习。", "明天星期六。", "谢谢老师。"), correctAnswer = "我和同学一起学习。", roundNumber = 5)
      )
    ),
    MicroLesson(
      id = "w4_n9_l2",
      nodeId = "w4_n9",
      title = "Đấu Trường Học Đường - Vòng 6 đến 10",
      subtitle = "Định vị bản đồ, luyện nói thuyết trình và viết chữ Hán đỉnh cao",
      type = "boss_challenge",
      order = 2,
      activities = listOf(
        LearningActivity("w4_n9_a6", ActivityType.LISTEN_HANZI, SkillType.LISTENING, listOf("w4_tushuguan"), "Vòng 6 (Định vị): Nghe tìm địa điểm thư viện:", audioText = "图书馆", options = listOf("图书馆", "教室", "食堂", "操场"), correctAnswer = "图书馆", roundNumber = 6),
        LearningActivity("w4_n9_a7", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w4_yiqi"), "Vòng 7: Tương tác rủ bạn thân:", pandaDialogue = "下课了，我们去操场还是食堂？", options = listOf("我们去食堂吃饭吧！", "我是老师。", "现在十点半。"), correctAnswer = "我们去食堂吃饭吧！", roundNumber = 7),
        LearningActivity("w4_n9_a8", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w4_xuexiao"), "Vòng 8 (Nói): Thuyết trình đời sống học đường:", hanziPrompt = "我是学生，我喜欢学校和同学，我们一起学习中文。", pinyinPrompt = "wǒ shì xuésheng, wǒ xǐhuan xuéxiào hé tóngxué, wǒmen yìqǐ xuéxí Zhōngwén.", audioText = "我是学生，我喜欢学校和同学，我们一起学习中文", correctAnswer = "我是学生，我喜欢学校和同学，我们一起学习中文", roundNumber = 8),
        LearningActivity("w4_n9_a9", ActivityType.WRITING, SkillType.WRITING, listOf("w4_xuexiao"), "Vòng 9 (Viết): Gõ chữ Hán từ Pinyin: \"xuéxiào\"", pinyinPrompt = "xuéxiào", correctAnswer = "学校", roundNumber = 9),
        LearningActivity("w4_n9_a10", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w4_yiqi"), "Vòng 10 (Ghép câu cuối): Ghép câu hoàn chỉnh:", sentenceWords = listOf("我们", "一起", "去", "教室", "上课", "吧"), targetSentence = "我们一起去教室上课吧", correctAnswer = "我们一起去教室上课吧", roundNumber = 10)
      )
    )
  )

  // -------------------------------------------------------------
  // ALL WORLD 4 NODE COURSES
  // -------------------------------------------------------------
  val world4NodeCourses = listOf(
    NodeCourseData(
      nodeId = "w4_n1",
      title = "School",
      subtitle = "学校 • Trường học & Lớp học",
      description = "Làm quen với trường học (学校), đại học (大学), phòng học (教室) và bàn ghế.",
      order = 1,
      vocabulary = listOf(
        itemMap["w4_xuexiao"]!!,
        itemMap["w4_daxue"]!!,
        itemMap["w4_jiaoshi"]!!,
        itemMap["w4_ke"]!!,
        itemMap["w4_shu"]!!,
        itemMap["w4_zhuozi"]!!,
        itemMap["w4_yizi"]!!,
        itemMap["w4_xuesheng"]!!,
        itemMap["w4_laoshi"]!!
      ),
      microLessons = node1Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n2",
      title = "My Classmates",
      subtitle = "我的同学 • Bạn cùng lớp",
      description = "Nói về bạn học (同学), bạn bè (朋友), bạn học nam/nữ và kết bạn.",
      order = 2,
      vocabulary = listOf(
        itemMap["w4_tongxue"]!!,
        itemMap["w4_pengyou"]!!,
        itemMap["w4_nan_tongxue"]!!,
        itemMap["w4_nv_tongxue"]!!,
        itemMap["w4_renshi"]!!,
        itemMap["w4_ye"]!!
      ),
      microLessons = node2Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n3",
      title = "Teachers & Students",
      subtitle = "老师和学生 • Thầy cô & Học sinh",
      description = "Hiểu rõ vai trò trong lớp: thầy dạy (教), trò học (学习), vào lớp (上课), tan lớp (下课).",
      order = 3,
      vocabulary = listOf(
        itemMap["w4_laoshi"]!!,
        itemMap["w4_xuesheng"]!!,
        itemMap["w4_jiao"]!!,
        itemMap["w4_xuexi"]!!,
        itemMap["w4_shangke"]!!,
        itemMap["w4_xiake"]!!,
        itemMap["w4_women"]!!
      ),
      microLessons = node3Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n4",
      title = "I Study Chinese",
      subtitle = "我学习中文 • Tôi học tiếng Trung",
      description = "Luyện 4 kỹ năng ngôn ngữ cốt lõi: Nghe (听), Nói (说), Đọc (读), Viết (写) và Chữ Hán (汉字).",
      order = 4,
      vocabulary = listOf(
        itemMap["w4_zhongwen"]!!,
        itemMap["w4_hanyu"]!!,
        itemMap["w4_shuo"]!!,
        itemMap["w4_ting"]!!,
        itemMap["w4_du"]!!,
        itemMap["w4_xie"]!!,
        itemMap["w4_hanzi"]!!
      ),
      microLessons = node4Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n5",
      title = "What Subject Do You Like?",
      subtitle = "你喜欢什么课？ • Môn học yêu thích",
      description = "Bày tỏ môn học yêu thích nhất: tiếng Trung (中文课), tiếng Anh (英语课), toán (数学课).",
      order = 5,
      vocabulary = listOf(
        itemMap["w4_ke"]!!,
        itemMap["w4_zhongwenke"]!!,
        itemMap["w4_yingyuke"]!!,
        itemMap["w4_shuxueke"]!!,
        itemMap["w4_zui"]!!,
        itemMap["w4_youyisi"]!!
      ),
      microLessons = node5Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n6",
      title = "Study Together",
      subtitle = "一起学习 • Cùng nhau học tập",
      description = "Rủ bạn cùng học: 一起学习吧, 你可以来吗？, 现在可以吗？, 我们去教室.",
      order = 6,
      vocabulary = listOf(
        itemMap["w4_yiqi"]!!,
        itemMap["w4_xuexi"]!!,
        itemMap["w4_qu"]!!,
        itemMap["w4_lai"]!!,
        itemMap["w4_xianzai"]!!,
        itemMap["w4_keyi"]!!,
        itemMap["w4_ba"]!!
      ),
      microLessons = node6Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n7",
      title = "Where Are You?",
      subtitle = "你在哪儿？ • Định vị không gian trường học",
      description = "Hỏi và chỉ vị trí: thư viện (图书馆), nhà ăn (食堂), sân trường (操场), ở đây/kia.",
      order = 7,
      vocabulary = listOf(
        itemMap["w4_zainar"]!!,
        itemMap["w4_tushuguan"]!!,
        itemMap["w4_shitang"]!!,
        itemMap["w4_caochang"]!!,
        itemMap["w4_zheli"]!!,
        itemMap["w4_nali"]!!
      ),
      microLessons = node7Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n8",
      title = "My School Life",
      subtitle = "我的学校生活 • Đời sống học đường",
      description = "Hội thoại tích hợp miêu tả trọn vẹn một ngày học tập sôi nổi cùng bạn bè và thầy cô.",
      order = 8,
      vocabulary = listOf(
        itemMap["w4_daxue"]!!,
        itemMap["w4_xuexiao"]!!,
        itemMap["w4_zhongwenke"]!!,
        itemMap["w4_tongxue"]!!,
        itemMap["w4_tushuguan"]!!,
        itemMap["w4_yiqi"]!!
      ),
      microLessons = node8Lessons
    ),
    NodeCourseData(
      nodeId = "w4_n9",
      title = "School Challenge",
      subtitle = "学校挑战 • Đấu trường Bậc thầy Học đường",
      description = "Trùm World 4: 10 vòng thử thách toàn diện từ giao tiếp đến định vị và thuyết trình.",
      order = 9,
      vocabulary = listOf(
        itemMap["w4_xuesheng"]!!,
        itemMap["w4_xuexiao"]!!,
        itemMap["w4_zhongwen"]!!,
        itemMap["w4_zhongwenke"]!!,
        itemMap["w4_tushuguan"]!!,
        itemMap["w4_yiqi"]!!
      ),
      microLessons = node9Lessons
    )
  )

  // -------------------------------------------------------------
  // WORLD 4 QUESTS
  // -------------------------------------------------------------
  val world4Quests = listOf(
    Quest("w4_q1", "world_4", "Gặp Gỡ Bạn Học", "Làm quen và hỏi thăm bạn cùng lớp", listOf(SkillType.CONVERSATION, SkillType.VOCABULARY), listOf("w4_n2"), xpReward = 30),
    Quest("w4_q2", "world_4", "Tìm Phòng Học", "Định vị phòng học và bàn ghế", listOf(SkillType.VOCABULARY, SkillType.READING), listOf("w4_n1"), xpReward = 25),
    Quest("w4_q3", "world_4", "Chào Thầy Cô", "Chào thầy cô và hiểu hiệu lệnh vào lớp", listOf(SkillType.LISTENING, SkillType.SPEAKING), listOf("w4_n3"), xpReward = 25),
    Quest("w4_q4", "world_4", "Luyện 4 Kỹ Năng", "Nghe, nói, đọc, viết tiếng Trung và chữ Hán", listOf(SkillType.SPEAKING, SkillType.WRITING), listOf("w4_n4"), xpReward = 35),
    Quest("w4_q5", "world_4", "Môn Học Yêu Thích", "Bày tỏ môn học bạn yêu thích nhất", listOf(SkillType.GRAMMAR, SkillType.SPEAKING), listOf("w4_n5"), xpReward = 30),
    Quest("w4_q6", "world_4", "Cùng Học Với Bạn", "Rủ bạn cùng học tại thư viện", listOf(SkillType.CONVERSATION, SkillType.LISTENING), listOf("w4_n6"), xpReward = 35),
    Quest("w4_q7", "world_4", "Khám Phá Sân Trường", "Tìm nhà ăn, sân tập và thư viện", listOf(SkillType.VOCABULARY, SkillType.READING), listOf("w4_n7"), xpReward = 30),
    Quest("w4_q8", "world_4", "Một Ngày Học Đường Hoàn Hảo", "Vượt qua thử thách Trùm trường học 10 vòng", listOf(SkillType.CONVERSATION, SkillType.SPEAKING, SkillType.WRITING), listOf("w4_n9"), xpReward = 50)
  )

  fun getNodeCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    val course = world4NodeCourses.find { it.nodeId == nodeId } ?: return null
    return if (learnerName.isNotBlank()) course.interpolateLearner(learnerName) else course
  }
}
