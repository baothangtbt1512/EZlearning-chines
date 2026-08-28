package com.example.data

import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.model.PandaEmotion
import com.example.model.SkillType
import com.example.model.interpolateLearner

object World3Curriculum {

  // -------------------------------------------------------------
  // ALL WORLD 3 LEARNING ITEMS
  // -------------------------------------------------------------
  val items = listOf(
    // Node 1: Family Basics
    LearningItem("w3_jia", "家", "jiā", "Nhà / Gia đình", usageNote = "Tổ ấm gia đình.", exampleSentence = "这是我的家。", examplePinyin = "zhè shì wǒ de jiā.", exampleTranslation = "Đây là nhà của tôi.", category = "family"),
    LearningItem("w3_jiaren", "家人", "jiārén", "Người nhà / Thành viên gia đình", usageNote = "Những người thân yêu trong nhà.", exampleSentence = "我爱我的家人。", examplePinyin = "wǒ ài wǒ de jiārén.", exampleTranslation = "Tôi yêu người nhà tôi.", category = "family"),
    LearningItem("w3_baba", "爸爸", "bàba", "Bố / Cha", usageNote = "Người cha kính yêu.", exampleSentence = "这是我的爸爸。", examplePinyin = "zhè shì wǒ de bàba.", exampleTranslation = "Đây là bố của tôi.", category = "family"),
    LearningItem("w3_mama", "妈妈", "māma", "Mẹ / Má", usageNote = "Người mẹ hiền hậu.", exampleSentence = "这是我的妈妈。", examplePinyin = "zhè shì wǒ de māma.", exampleTranslation = "Đây là mẹ của tôi.", category = "family"),
    LearningItem("w3_gege", "哥哥", "gēge", "Anh trai", usageNote = "Anh trai lớn hơn.", exampleSentence = "他是我哥哥。", examplePinyin = "tā shì wǒ gēge.", exampleTranslation = "Anh ấy là anh trai tôi.", category = "family"),
    LearningItem("w3_jiejie", "姐姐", "jiějie", "Chị gái", usageNote = "Chị gái trong nhà.", exampleSentence = "她是我姐姐。", examplePinyin = "tā shì wǒ jiějie.", exampleTranslation = "Cô ấy là chị gái tôi.", category = "family"),
    LearningItem("w3_didi", "弟弟", "dìdi", "Em trai", usageNote = "Em trai nghịch ngợm đáng yêu.", exampleSentence = "他是我弟弟。", examplePinyin = "tā shì wǒ dìdi.", exampleTranslation = "Cậu ấy là em trai tôi.", category = "family"),
    LearningItem("w3_meimei", "妹妹", "mèimei", "Em gái", usageNote = "Em gái dễ thương.", exampleSentence = "这是我妹妹。", examplePinyin = "zhè shì wǒ mèimei.", exampleTranslation = "Đây là em gái tôi.", category = "family"),

    // Node 2: Who is this? (他是谁？)
    LearningItem("w3_shei", "谁", "shéi", "Ai (Who)", usageNote = "Đại từ nghi vấn hỏi về danh tính người.", exampleSentence = "他是谁？", examplePinyin = "tā shì shéi?", exampleTranslation = "Anh ấy là ai?", category = "question"),
    LearningItem("w3_ta_he", "他", "tā", "Anh ấy / Ông ấy", usageNote = "Đại từ ngôi thứ 3 chỉ nam giới.", exampleSentence = "他是我爸爸。", examplePinyin = "tā shì wǒ bàba.", exampleTranslation = "Ông ấy là bố tôi.", category = "pronoun"),
    LearningItem("w3_ta_she", "她", "tā", "Cô ấy / Bà ấy", usageNote = "Đại từ ngôi thứ 3 chỉ nữ giới.", exampleSentence = "她是我妈妈。", examplePinyin = "tā shì wǒ māma.", exampleTranslation = "Bà ấy là mẹ tôi.", category = "pronoun"),
    LearningItem("w3_shi", "是", "shì", "Là / Đúng", usageNote = "Động từ liên kết quan hệ (A 是 B).", exampleSentence = "我是学生。", examplePinyin = "wǒ shì xuésheng.", exampleTranslation = "Tôi là học sinh.", category = "verb"),
    LearningItem("w3_zhe", "这", "zhè", "Đây / Cái này (This)", usageNote = "Đại từ chỉ định ở gần người nói.", exampleSentence = "这是我的妈妈。", examplePinyin = "zhè shì wǒ de māma.", exampleTranslation = "Đây là mẹ tôi.", category = "pronoun"),
    LearningItem("w3_na", "那", "nà", "Đó / Cái kia (That)", usageNote = "Đại từ chỉ định ở xa người nói.", exampleSentence = "那是我爸爸。", examplePinyin = "nà shì wǒ bàba.", exampleTranslation = "Đó là bố tôi.", category = "pronoun"),

    // Node 3: How many people? (你家有几个人？)
    LearningItem("w3_you", "有", "yǒu", "Có (have)", usageNote = "Động từ biểu thị sở hữu hoặc tồn tại.", exampleSentence = "我家有四个人。", examplePinyin = "wǒ jiā yǒu sì gè rén.", exampleTranslation = "Nhà tôi có 4 người.", category = "verb"),
    LearningItem("w3_ji", "几", "jǐ", "Mấy / Bao nhiêu", usageNote = "Từ hỏi số lượng nhỏ.", exampleSentence = "你家有几个人？", examplePinyin = "nǐ jiā yǒu jǐ gè rén?", exampleTranslation = "Nhà bạn có mấy người?", category = "question"),
    LearningItem("w3_ge", "个", "gè", "Cái / Người (Lượng từ)", usageNote = "Lượng từ thông dụng nhất trong tiếng Trung.", exampleSentence = "三个人。", examplePinyin = "sān gè rén.", exampleTranslation = "Ba người.", category = "measure"),
    LearningItem("w3_ren", "人", "rén", "Người", usageNote = "Nhân - con người.", exampleSentence = "四个人。", examplePinyin = "sì gè rén.", exampleTranslation = "Bốn người.", category = "noun"),

    // Node 4: Age in Home (你几岁？)
    LearningItem("w3_sui", "岁", "suì", "Tuổi", usageNote = "Lượng từ chỉ tuổi tác.", exampleSentence = "我十八岁。", examplePinyin = "wǒ shíbā suì.", exampleTranslation = "Tôi 18 tuổi.", category = "age"),
    LearningItem("w3_duoda", "多大", "duō dà", "Bao nhiêu tuổi", usageNote = "Hỏi tuổi người lớn.", exampleSentence = "你多大？", examplePinyin = "nǐ duō dà?", exampleTranslation = "Bạn bao nhiêu tuổi?", category = "age"),
    LearningItem("w3_jinnian", "今年", "jīnnián", "Năm nay", usageNote = "Năm hiện tại.", exampleSentence = "我今年十八岁。", examplePinyin = "wǒ jīnnián shíbā suì.", exampleTranslation = "Năm nay tôi 18 tuổi.", category = "time"),

    // Node 5: House & Rooms (我的家)
    LearningItem("w3_fangzi", "房子", "fángzi", "Ngôi nhà / Căn nhà", usageNote = "Căn nhà, tòa nhà ở.", exampleSentence = "这是我的房子。", examplePinyin = "zhè shì wǒ de fángzi.", exampleTranslation = "Đây là ngôi nhà của tôi.", category = "house"),
    LearningItem("w3_fangjian", "房间", "fángjiān", "Căn phòng", usageNote = "Phòng ở trong nhà.", exampleSentence = "我家有三个房间。", examplePinyin = "wǒ jiā yǒu sān gè fángjiān.", exampleTranslation = "Nhà tôi có 3 phòng.", category = "house"),
    LearningItem("w3_keting", "客厅", "kètīng", "Phòng khách", usageNote = "Nơi tiếp khách và sinh hoạt chung gia đình.", exampleSentence = "客厅很大。", examplePinyin = "kètīng hěn dà.", exampleTranslation = "Phòng khách rất rộng lớn.", category = "house"),
    LearningItem("w3_woshi", "卧室", "wòshì", "Phòng ngủ", usageNote = "Nơi nghỉ ngơi ấm áp.", exampleSentence = "这是我的卧室。", examplePinyin = "zhè shì wǒ de wòshì.", exampleTranslation = "Đây là phòng ngủ của tôi.", category = "house"),
    LearningItem("w3_chufang", "厨房", "chúfáng", "Phòng bếp", usageNote = "Nơi nấu nướng món ngon.", exampleSentence = "妈妈在厨房。", examplePinyin = "māma zài chúfáng.", exampleTranslation = "Mẹ đang ở trong bếp.", category = "house"),
    LearningItem("w3_weishengjian", "卫生间", "wèishēngjiān", "Phòng vệ sinh / Phòng tắm", usageNote = "Nhà vệ sinh sạch sẽ.", exampleSentence = "卫生间在这里。", examplePinyin = "wèishēngjiān zài zhèlǐ.", exampleTranslation = "Phòng vệ sinh ở đây.", category = "house"),

    // Node 6: Locations & Objects (在哪里？)
    LearningItem("w3_zai", "在", "zài", "Ở / Tại (At/In)", usageNote = "Chỉ vị trí tồn tại (Vật + 在 + Vị trí).", exampleSentence = "书在桌子上。", examplePinyin = "shū zài zhuōzi shàng.", exampleTranslation = "Sách ở trên bàn.", category = "grammar"),
    LearningItem("w3_nali", "哪里", "nǎlǐ", "Ở đâu (Where)", usageNote = "Đại từ nghi vấn hỏi vị trí.", exampleSentence = "书在哪里？", examplePinyin = "shū zài nǎlǐ?", exampleTranslation = "Sách ở đâu?", category = "question"),
    LearningItem("w3_zheli", "这里", "zhèlǐ", "Ở đây / Chỗ này (Here)", usageNote = "Vị trí gần.", exampleSentence = "我在这里。", examplePinyin = "wǒ zài zhèlǐ.", exampleTranslation = "Tôi ở đây.", category = "location"),
    LearningItem("w3_nali_there", "那里", "nàlǐ", "Ở kia / Đằng đó (There)", usageNote = "Vị trí xa.", exampleSentence = "爸爸在那里。", examplePinyin = "bàba zài nàlǐ.", exampleTranslation = "Bố ở đằng kia.", category = "location"),
    LearningItem("w3_zhuozi", "桌子", "zhuōzi", "Cái bàn", usageNote = "Bàn làm việc, bàn ăn.", exampleSentence = "这是桌子。", examplePinyin = "zhè shì zhuōzi.", exampleTranslation = "Đây là cái bàn.", category = "furniture"),
    LearningItem("w3_yizi", "椅子", "yǐzi", "Cái ghế", usageNote = "Ghế ngồi.", exampleSentence = "椅子在房间里。", examplePinyin = "yǐzi zài fángjiān lǐ.", exampleTranslation = "Ghế ở trong phòng.", category = "furniture"),
    LearningItem("w3_shu", "书", "shū", "Quyển sách", usageNote = "Sách vở học tập.", exampleSentence = "书在桌子上。", examplePinyin = "shū zài zhuōzi shàng.", exampleTranslation = "Sách ở trên bàn.", category = "object"),

    // Node 7: Describing Family (我的家人)
    LearningItem("w3_hen", "很", "hěn", "Rất", usageNote = "Phó từ chỉ mức độ trước tính từ.", exampleSentence = "我妈妈很漂亮。", examplePinyin = "wǒ māma hěn piàoliang.", exampleTranslation = "Mẹ tôi rất xinh đẹp.", category = "grammar"),
    LearningItem("w3_da", "大", "dà", "Lớn / To (Big)", usageNote = "Kích thước rộng lớn.", exampleSentence = "房子很大。", examplePinyin = "fángzi hěn dà.", exampleTranslation = "Ngôi nhà rất to.", category = "adjective"),
    LearningItem("w3_xiao", "小", "xiǎo", "Nhỏ / Bé (Small)", usageNote = "Kích thước nhỏ nhắn.", exampleSentence = "房间很小。", examplePinyin = "fángjiān hěn xiǎo.", exampleTranslation = "Căn phòng nhỏ nhắn.", category = "adjective"),
    LearningItem("w3_gao", "高", "gāo", "Cao (Tall)", usageNote = "Chiều cao vượt trội.", exampleSentence = "我爸爸很高。", examplePinyin = "wǒ bàba hěn gāo.", exampleTranslation = "Bố tôi rất cao.", category = "adjective"),
    LearningItem("w3_piaoliang", "漂亮", "piàoliang", "Xinh đẹp (Beautiful)", usageNote = "Khen ngợi vẻ đẹp duyên dáng.", exampleSentence = "我妈妈很漂亮。", examplePinyin = "wǒ māma hěn piàoliang.", exampleTranslation = "Mẹ tôi rất xinh đẹp.", category = "adjective"),
    LearningItem("w3_keai", "可爱", "kě'ài", "Đáng yêu / Dễ thương (Cute)", usageNote = "Khen trẻ em, thú cưng đáng yêu.", exampleSentence = "我弟弟很可爱。", examplePinyin = "wǒ dìdi hěn kě'ài.", exampleTranslation = "Em trai tôi rất đáng yêu.", category = "adjective")
  )

  val itemMap: Map<String, LearningItem> by lazy {
    items.associateBy { it.id }
  }

  // -------------------------------------------------------------
  // NODE 1: MY FAMILY (我的家人)
  // -------------------------------------------------------------
  private val node1Lessons = listOf(
    MicroLesson(
      id = "w3_n1_l1",
      nodeId = "w3_n1",
      title = "Khám Phá Thành Viên Gia Đình",
      subtitle = "Ôn luyện và nhận biết sâu các danh từ cha mẹ, anh chị em",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n1_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_jia", "w3_jiaren", "w3_baba", "w3_mama"), "Khám phá danh xưng gia đình", audioText = "家 家人 爸爸 妈妈", correctAnswer = ""),
        LearningActivity("w3_n1_a2", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_gege", "w3_jiejie", "w3_didi", "w3_meimei"), "Khám phá anh chị em", audioText = "哥哥 姐姐 弟弟 妹妹", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n1_l2",
      nodeId = "w3_n1",
      title = "Luyện Nghe Danh Từ Gia Đình",
      subtitle = "Phân biệt phát âm bàba, māma, gēge, jiějie",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n1_a3", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_mama"), "Nghe và chọn Pinyin:", audioText = "妈妈", options = listOf("māma", "bàba", "gēge", "mèimei"), correctAnswer = "māma"),
        LearningActivity("w3_n1_a4", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_baba"), "Nghe và chọn chữ Hán cho Bố:", audioText = "爸爸", options = listOf("爸爸", "妈妈", "哥哥", "弟弟"), correctAnswer = "爸爸")
      )
    ),
    MicroLesson(
      id = "w3_n1_l3",
      nodeId = "w3_n1",
      title = "Ghép Câu Giới Thiệu Gia Đình",
      subtitle = "Tạo câu: 这是我的妈妈 & 他是我哥哥",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n1_a5", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_mama"), "Ghép câu: \"Đây là mẹ của tôi.\"", sentenceWords = listOf("这是", "我的", "妈妈", "爸爸", "她"), targetSentence = "这是我的妈妈", correctAnswer = "这是我的妈妈"),
        LearningActivity("w3_n1_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_gege"), "Gấu trúc chỉ vào bức ảnh và hỏi:", pandaDialogue = "这是你的哥哥吗？", options = listOf("是的，他是我哥哥。", "我是学生。", "谢谢！"), correctAnswer = "是的，他是我哥哥。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 2: WHO IS THIS? (他是谁？)
  // -------------------------------------------------------------
  private val node2Lessons = listOf(
    MicroLesson(
      id = "w3_n2_l1",
      nodeId = "w3_n2",
      title = "Khám Phá Đại Từ Nghi Vấn",
      subtitle = "Học từ \"谁\" (Ai) và \"这\" (Đây) / \"那\" (Đó)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n2_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_shei", "w3_ta_he", "w3_ta_she", "w3_shi", "w3_zhe", "w3_na"), "Khám phá từ vựng hỏi danh tính", audioText = "谁 他 她 是 这 那", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n2_l2",
      nodeId = "w3_n2",
      title = "Luyện Nghe Câu Hỏi Danh Tính",
      subtitle = "Nghe và nhận diện câu \"他是谁？\" (Anh ấy là ai?)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n2_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_shei"), "Nghe và chọn Pinyin đúng:", audioText = "他是谁？", options = listOf("tā shì shéi?", "tā shì māma?", "zhè shì shéi?", "nǐ shì shéi?"), correctAnswer = "tā shì shéi?"),
        LearningActivity("w3_n2_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_shei"), "Nghe và chọn chữ Hán cho câu hỏi:", audioText = "她是谁？", options = listOf("她是谁？", "他是谁？", "这是什么？", "你在哪里？"), correctAnswer = "她是谁？")
      )
    ),
    MicroLesson(
      id = "w3_n2_l3",
      nodeId = "w3_n2",
      title = "Ghép Câu & Hội Thoại Hỏi Ai",
      subtitle = "Tạo câu: 他是谁？ 他是我爸爸。",
      type = "conversation_sentence",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n2_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_ta_he"), "Ghép câu trả lời: \"Ông ấy là bố tôi.\"", sentenceWords = listOf("他", "是", "我", "爸爸", "谁"), targetSentence = "他是我爸爸", correctAnswer = "他是我爸爸"),
        LearningActivity("w3_n2_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_shei"), "Gấu trúc hỏi bạn:", pandaDialogue = "她是谁？", options = listOf("她是我妈妈。", "我是十八岁。", "今天星期五。"), correctAnswer = "她是我妈妈。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 3: HOW MANY PEOPLE? (你家有几个人？)
  // -------------------------------------------------------------
  private val node3Lessons = listOf(
    MicroLesson(
      id = "w3_n3_l1",
      nodeId = "w3_n3",
      title = "Khám Phá Số Lượng Thành Viên",
      subtitle = "Học \"有\" (Có), \"几\" (Mấy), \"个\" (Lượng từ)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n3_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_you", "w3_ji", "w3_ge", "w3_ren", "w3_jia"), "Khám phá từ vựng đếm thành viên", audioText = "有 几 个 人 家", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n3_l2",
      nodeId = "w3_n2",
      title = "Luyện Nghe Số Lượng",
      subtitle = "Nghe câu: 我家有四个人",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n3_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_you"), "Nghe và chọn số lượng đúng:", audioText = "四个人", options = listOf("sì gè rén (4 người)", "sān gè rén (3 người)", "wǔ gè rén (5 người)", "liù gè rén (6 người)"), correctAnswer = "sì gè rén (4 người)"),
        LearningActivity("w3_n3_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_you"), "Nghe và chọn câu chữ Hán:", audioText = "我家有四个人", options = listOf("我家有四个人", "我家有三个人", "我是学生", "这是我爸爸"), correctAnswer = "我家有四个人")
      )
    ),
    MicroLesson(
      id = "w3_n3_l3",
      nodeId = "w3_n3",
      title = "Ghép Câu & Hội Thoại Đếm Người",
      subtitle = "Hỏi và đáp số thành viên gia đình trọn vẹn",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n3_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_you"), "Ghép câu: \"Nhà tôi có bốn người.\"", sentenceWords = listOf("我家", "有", "四", "个", "人", "是"), targetSentence = "我家有四个人", correctAnswer = "我家有四个人"),
        LearningActivity("w3_n3_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_ji"), "Gấu trúc hỏi về gia đình bạn:", pandaDialogue = "你家有几个人？", options = listOf("我家有四个人。", "现在八点。", "我喜欢喝茶。"), correctAnswer = "我家有四个人。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 4: HOW OLD? (你几岁？)
  // -------------------------------------------------------------
  private val node4Lessons = listOf(
    MicroLesson(
      id = "w3_n4_l1",
      nodeId = "w3_n4",
      title = "Khám Phá Tuổi Trong Gia Đình",
      subtitle = "Học cách giới thiệu tuổi tác của từng người thân",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n4_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_sui", "w3_duoda", "w3_jinnian"), "Khám phá tuổi tác người thân", audioText = "岁 多大 今年", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n4_l2",
      nodeId = "w3_n4",
      title = "Luyện Nghe & Ghép Câu Tuổi",
      subtitle = "Tạo câu: 我今年十八岁",
      type = "sentence_builder",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n4_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_sui"), "Nghe và chọn tuổi:", audioText = "十八岁", options = listOf("shíbā suì", "èrshí suì", "shí suì", "jiǔ suì"), correctAnswer = "shíbā suì"),
        LearningActivity("w3_n4_a3", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_sui"), "Ghép câu: \"Năm nay tôi mười tám tuổi.\"", sentenceWords = listOf("我", "今年", "十八", "岁", "有", "是"), targetSentence = "我今年十八岁", correctAnswer = "我今年十八岁"),
        LearningActivity("w3_n4_a4", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_duoda"), "Gấu trúc hỏi tuổi bạn:", pandaDialogue = "你今年多大？", options = listOf("我今年十八岁。", "我家有四个人。", "再见！"), correctAnswer = "我今年十八岁。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 5: MY HOME (我的家)
  // -------------------------------------------------------------
  private val node5Lessons = listOf(
    MicroLesson(
      id = "w3_n5_l1",
      nodeId = "w3_n5",
      title = "Khám Phá Căn Nhà & Các Phòng",
      subtitle = "Học phòng khách (客厅), phòng ngủ (卧室), nhà bếp (厨房)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n5_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_fangzi", "w3_fangjian", "w3_keting", "w3_woshi", "w3_chufang", "w3_weishengjian"), "Khám phá không gian trong nhà", audioText = "房子 房间 客厅 卧室 厨房 卫生间", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n5_l2",
      nodeId = "w3_n5",
      title = "Luyện Nghe Nhận Diện Phòng",
      subtitle = "Nghe và phân biệt 客厅, 卧室, 厨房",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n5_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_keting"), "Nghe và chọn Pinyin của \"Phòng khách\":", audioText = "客厅", options = listOf("kètīng", "wòshì", "chúfáng", "fángjiān"), correctAnswer = "kètīng"),
        LearningActivity("w3_n5_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_woshi"), "Nghe và chọn chữ Hán cho \"Phòng ngủ\":", audioText = "卧室", options = listOf("卧室", "客厅", "厨房", "卫生间"), correctAnswer = "卧室")
      )
    ),
    MicroLesson(
      id = "w3_n5_l3",
      nodeId = "w3_n5",
      title = "Ghép Câu & Thăm Nhà Cùng Gấu Trúc",
      subtitle = "Tạo câu: 这是我的卧室",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n5_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_woshi"), "Ghép câu: \"Đây là phòng ngủ của tôi.\"", sentenceWords = listOf("这是", "我的", "卧室", "客厅", "在"), targetSentence = "这是我的卧室", correctAnswer = "这是我的卧室"),
        LearningActivity("w3_n5_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_fangjian"), "Gấu trúc trầm trồ khen ngợi:", pandaDialogue = "你的房间真漂亮！", options = listOf("谢谢！这是我的卧室。", "我是学生。", "现在八点。"), correctAnswer = "谢谢！这是我的卧室。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 6: WHERE IS IT? (在哪里？)
  // -------------------------------------------------------------
  private val node6Lessons = listOf(
    MicroLesson(
      id = "w3_n6_l1",
      nodeId = "w3_n6",
      title = "Khám Phá Vị Trí & Đồ Vật",
      subtitle = "Học ở đâu (在哪里), cái bàn (桌子), cái ghế (椅子), quyển sách (书)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n6_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_zai", "w3_nali", "w3_zheli", "w3_nali_there", "w3_zhuozi", "w3_yizi", "w3_shu"), "Khám phá từ vựng vị trí & đồ đạc", audioText = "在 哪里 这里 那里 桌子 椅子 书", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n6_l2",
      nodeId = "w3_n6",
      title = "Luyện Nghe Vị Trí Đồ Vật",
      subtitle = "Nghe câu: 书在桌子上",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n6_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_nali"), "Nghe và chọn Pinyin của \"Ở đâu\":", audioText = "在哪里", options = listOf("zài nǎlǐ", "zài zhèlǐ", "zài nàlǐ", "zài fángjiān"), correctAnswer = "zài nǎlǐ"),
        LearningActivity("w3_n6_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_shu"), "Nghe và chọn câu chữ Hán:", audioText = "书在桌子上", options = listOf("书在桌子上", "爸爸在客厅", "我在家", "这是椅子"), correctAnswer = "书在桌子上")
      )
    ),
    MicroLesson(
      id = "w3_n6_l3",
      nodeId = "w3_n6",
      title = "Ghép Câu Vị Trí & Hội Thoại",
      subtitle = "Hỏi và đáp: 书在哪里？ 书在桌子上。",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n6_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_zai"), "Ghép câu: \"Sách ở trên bàn.\"", sentenceWords = listOf("书", "在", "桌子", "上", "哪里", "是"), targetSentence = "书在桌子上", correctAnswer = "书在桌子上"),
        LearningActivity("w3_n6_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_nali"), "Gấu trúc tìm đồ và hỏi:", pandaDialogue = "书在哪里？", options = listOf("书在桌子上。", "我叫 {{learner.name}}。", "今天星期五。"), correctAnswer = "书在桌子上。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 7: DESCRIBING MY FAMILY (我的家人)
  // -------------------------------------------------------------
  private val node7Lessons = listOf(
    MicroLesson(
      id = "w3_n7_l1",
      nodeId = "w3_n7",
      title = "Khám Phá Tính Từ Miêu Tả",
      subtitle = "Học tính từ: rất (很), to (大), nhỏ (小), cao (高), xinh đẹp (漂亮), đáng yêu (可爱)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n7_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w3_hen", "w3_da", "w3_xiao", "w3_gao", "w3_piaoliang", "w3_keai"), "Khám phá các tính từ miêu tả", audioText = "很 大 小 高 漂亮 可爱", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w3_n7_l2",
      nodeId = "w3_n7",
      title = "Luyện Nghe Tính Từ",
      subtitle = "Nghe câu: 我妈妈很漂亮 & 我爸爸很高",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n7_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w3_piaoliang"), "Nghe và chọn Pinyin của \"Xinh đẹp\":", audioText = "漂亮", options = listOf("piàoliang", "kě'ài", "gāoxìng", "rènshi"), correctAnswer = "piàoliang"),
        LearningActivity("w3_n7_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w3_keai"), "Nghe và chọn chữ Hán cho \"Đáng yêu\":", audioText = "可爱", options = listOf("可爱", "漂亮", "高兴", "喜欢"), correctAnswer = "可爱")
      )
    ),
    MicroLesson(
      id = "w3_n7_l3",
      nodeId = "w3_n7",
      title = "Ghép Câu Miêu Tả Người Thân",
      subtitle = "Tạo câu: 我妈妈很漂亮 & 我弟弟很可爱",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w3_n7_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_piaoliang"), "Ghép câu: \"Mẹ tôi rất xinh đẹp.\"", sentenceWords = listOf("我", "妈妈", "很", "漂亮", "高", "大"), targetSentence = "我妈妈很漂亮", correctAnswer = "我妈妈很漂亮"),
        LearningActivity("w3_n7_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_keai"), "Gấu trúc khen em trai bạn:", pandaDialogue = "你的弟弟真可爱！", options = listOf("谢谢！我弟弟很可爱。", "他在学校。", "现在三点。"), correctAnswer = "谢谢！我弟弟很可爱。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 8: FAMILY & HOME (我的家) - INTEGRATION NODE
  // -------------------------------------------------------------
  private val node8Lessons = listOf(
    MicroLesson(
      id = "w3_n8_l1",
      nodeId = "w3_n8",
      title = "Hội Thoại Tích Hợp Gia Đình & Tổ Ấm",
      subtitle = "Kết hợp số lượng người, phòng ốc, vị trí và miêu tả",
      type = "integration_dialogue",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n8_a1", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_you"), "Gấu trúc hỏi số người:", pandaDialogue = "你家有几个人？", options = listOf("我家有四个人。", "现在八点。", "我喝水。"), correctAnswer = "我家有四个人。"),
        LearningActivity("w3_n8_a2", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_shei"), "Gấu trúc hỏi người trong ảnh:", pandaDialogue = "他是谁？", options = listOf("他是我爸爸。", "这是桌子。", "我十八岁。"), correctAnswer = "他是我爸爸。"),
        LearningActivity("w3_n8_a3", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_zai"), "Gấu trúc hỏi mẹ bạn ở đâu:", pandaDialogue = "你妈妈呢？", options = listOf("她在家里。", "今天星期五。", "谢谢！"), correctAnswer = "她在家里。"),
        LearningActivity("w3_n8_a4", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w3_fangjian"), "Gấu trúc hỏi số phòng:", pandaDialogue = "你家有几个房间？", options = listOf("我家有三个房间。", "我喜欢中文。", "她是医生。"), correctAnswer = "我家有三个房间。")
      )
    ),
    MicroLesson(
      id = "w3_n8_l2",
      nodeId = "w3_n8",
      title = "Luyện Nói & Đọc Hiểu Tổng Hợp",
      subtitle = "Thuyết trình về gia đình và căn nhà của bạn",
      type = "speaking_reading",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n8_a5", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w3_jiaren"), "Đọc câu tự giới thiệu gia đình:", hanziPrompt = "我家有四个人，我妈妈很漂亮。", pinyinPrompt = "wǒ jiā yǒu sì gè rén, wǒ māma hěn piàoliang.", audioText = "我家有四个人，我妈妈很漂亮", correctAnswer = "我家有四个人，我妈妈很漂亮"),
        LearningActivity("w3_n8_a6", ActivityType.WRITING, SkillType.WRITING, listOf("w3_jia"), "Gõ chữ Hán cho \"Nhà\" (jiā):", pinyinPrompt = "jiā", correctAnswer = "家")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 9: MY HOME BOSS (我的家挑战) - WORLD 3 BOSS MASTERY
  // -------------------------------------------------------------
  private val node9Lessons = listOf(
    MicroLesson(
      id = "w3_n9_l1",
      nodeId = "w3_n9",
      title = "Đấu Trường Ngôi Nhà & Gia Đình - Vòng 1 đến 5",
      subtitle = "Thử thách toàn diện danh tính, số lượng, tuổi và vị trí",
      type = "boss_challenge",
      order = 1,
      activities = listOf(
        LearningActivity("w3_n9_a1", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w3_you"), "Vòng 1: Hỏi số lượng thành viên:", pandaDialogue = "你家有几个人？", options = listOf("我家有四个人。", "现在八点。", "我是学生。"), correctAnswer = "我家有四个人。", roundNumber = 1),
        LearningActivity("w3_n9_a2", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w3_shei"), "Vòng 2: Hỏi danh tính người thân:", pandaDialogue = "他是谁？", options = listOf("他是我爸爸。", "今天星期五。", "我很好。"), correctAnswer = "他是我爸爸。", roundNumber = 2),
        LearningActivity("w3_n9_a3", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w3_sui"), "Vòng 3: Hỏi tuổi tác:", pandaDialogue = "你几岁？", options = listOf("我十八岁。", "这是客厅。", "我喜欢喝茶。"), correctAnswer = "我十八岁。", roundNumber = 3),
        LearningActivity("w3_n9_a4", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w3_nali"), "Vòng 4: Hỏi vị trí sách vở:", pandaDialogue = "书在哪里？", options = listOf("书在桌子上。", "我叫 {{learner.name}}。", "明天见。"), correctAnswer = "书在桌子上。", roundNumber = 4),
        LearningActivity("w3_n9_a5", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w3_piaoliang"), "Vòng 5: Miêu tả mẹ:", pandaDialogue = "你妈妈怎么样？", options = listOf("我妈妈很漂亮。", "现在三点半。", "我有四个人。"), correctAnswer = "我妈妈很漂亮。", roundNumber = 5)
      )
    ),
    MicroLesson(
      id = "w3_n9_l2",
      nodeId = "w3_n9",
      title = "Đấu Trường Ngôi Nhà & Gia Đình - Vòng 6 đến 8",
      subtitle = "Thuyết trình gia đình đỉnh cao, đọc hiểu và gõ chữ Hán",
      type = "boss_challenge",
      order = 2,
      activities = listOf(
        LearningActivity("w3_n9_a6", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w3_jiaren"), "Vòng 6 (Nói): Thuyết trình gia đình:", hanziPrompt = "我家有四个人。这是我爸爸，这是我妈妈。", pinyinPrompt = "wǒ jiā yǒu sì gè rén. zhè shì wǒ bàba, zhè shì wǒ māma.", audioText = "我家有四个人。这是我爸爸，这是我妈妈", correctAnswer = "我家有四个人。这是我爸爸，这是我妈妈", roundNumber = 6),
        LearningActivity("w3_n9_a7", ActivityType.WRITING, SkillType.WRITING, listOf("w3_you"), "Vòng 7 (Viết): Gõ câu hoàn chỉnh từ Pinyin: \"wǒ jiā yǒu sì gè rén\"", pinyinPrompt = "wǒ jiā yǒu sì gè rén", correctAnswer = "我家有四个人", roundNumber = 7),
        LearningActivity("w3_n9_a8", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w3_jia"), "Vòng 8 (Ghép câu cuối): Ghép câu hoàn chỉnh:", sentenceWords = listOf("我", "爱", "我的", "家", "和", "家人"), targetSentence = "我爱我的家和家人", correctAnswer = "我爱我的家和家人", roundNumber = 8)
      )
    )
  )

  // -------------------------------------------------------------
  // ALL WORLD 3 NODE COURSES
  // -------------------------------------------------------------
  val world3NodeCourses = listOf(
    NodeCourseData(
      nodeId = "w3_n1",
      title = "My Family",
      subtitle = "我的家人 • Gia đình thân thương",
      description = "Khám phá và nhận diện cha, mẹ, anh, chị, em trong tổ ấm gia đình.",
      order = 1,
      vocabulary = listOf(
        itemMap["w3_jia"]!!,
        itemMap["w3_jiaren"]!!,
        itemMap["w3_baba"]!!,
        itemMap["w3_mama"]!!,
        itemMap["w3_gege"]!!,
        itemMap["w3_jiejie"]!!,
        itemMap["w3_didi"]!!,
        itemMap["w3_meimei"]!!
      ),
      microLessons = node1Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n2",
      title = "Who Is This?",
      subtitle = "他是谁？ • Xác định danh tính người thân",
      description = "Hỏi và đáp về người thân: 他是谁？ 他是我爸爸。 她是谁？ 她是我妈妈。",
      order = 2,
      vocabulary = listOf(
        itemMap["w3_shei"]!!,
        itemMap["w3_ta_he"]!!,
        itemMap["w3_ta_she"]!!,
        itemMap["w3_shi"]!!,
        itemMap["w3_zhe"]!!,
        itemMap["w3_na"]!!
      ),
      microLessons = node2Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n3",
      title = "How Many People?",
      subtitle = "你家有几个人？ • Số lượng thành viên",
      description = "Hỏi và đếm số người trong gia đình: 你家有几个人？ 我家有四个人。",
      order = 3,
      vocabulary = listOf(
        itemMap["w3_you"]!!,
        itemMap["w3_ji"]!!,
        itemMap["w3_ge"]!!,
        itemMap["w3_ren"]!!,
        itemMap["w3_jia"]!!
      ),
      microLessons = node3Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n4",
      title = "How Old?",
      subtitle = "你几岁？ • Tuổi tác người thân",
      description = "Hỏi tuổi và giới thiệu năm nay bao nhiêu tuổi: 你几岁？ 我十八岁。",
      order = 4,
      vocabulary = listOf(
        itemMap["w3_sui"]!!,
        itemMap["w3_duoda"]!!,
        itemMap["w3_jinnian"]!!
      ),
      microLessons = node4Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n5",
      title = "My Home",
      subtitle = "我的家 • Căn nhà & Các gian phòng",
      description = "Khám phá phòng khách (客厅), phòng ngủ (卧室), phòng bếp (厨房), nhà vệ sinh (卫生间).",
      order = 5,
      vocabulary = listOf(
        itemMap["w3_fangzi"]!!,
        itemMap["w3_fangjian"]!!,
        itemMap["w3_keting"]!!,
        itemMap["w3_woshi"]!!,
        itemMap["w3_chufang"]!!,
        itemMap["w3_weishengjian"]!!
      ),
      microLessons = node5Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n6",
      title = "Where Is It?",
      subtitle = "在哪里？ • Vị trí đồ vật & Phòng ốc",
      description = "Hỏi và định vị đồ đạc: 书在哪里？ 书在桌子上。 我在家。",
      order = 6,
      vocabulary = listOf(
        itemMap["w3_zai"]!!,
        itemMap["w3_nali"]!!,
        itemMap["w3_zheli"]!!,
        itemMap["w3_nali_there"]!!,
        itemMap["w3_zhuozi"]!!,
        itemMap["w3_yizi"]!!,
        itemMap["w3_shu"]!!
      ),
      microLessons = node6Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n7",
      title = "Describing My Family",
      subtitle = "我的家人 • Tính từ miêu tả",
      description = "Miêu tả người thân: 我妈妈很漂亮, 我弟弟很可爱, 我爸爸很高.",
      order = 7,
      vocabulary = listOf(
        itemMap["w3_hen"]!!,
        itemMap["w3_da"]!!,
        itemMap["w3_xiao"]!!,
        itemMap["w3_gao"]!!,
        itemMap["w3_piaoliang"]!!,
        itemMap["w3_keai"]!!
      ),
      microLessons = node7Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n8",
      title = "Family & Home",
      subtitle = "我的家 • Hội thoại tích hợp toàn diện",
      description = "Kết hợp gia đình, số người, phòng ốc và vị trí trong cuộc trò chuyện liền mạch.",
      order = 8,
      vocabulary = listOf(
        itemMap["w3_jia"]!!,
        itemMap["w3_baba"]!!,
        itemMap["w3_mama"]!!,
        itemMap["w3_you"]!!,
        itemMap["w3_fangjian"]!!,
        itemMap["w3_zai"]!!
      ),
      microLessons = node8Lessons
    ),
    NodeCourseData(
      nodeId = "w3_n9",
      title = "My Home Challenge",
      subtitle = "我的家挑战 • Đấu trường Bậc thầy Tổ ấm",
      description = "Thử thách trùm 8 vòng tổng hợp toàn diện kiến thức World 3.",
      order = 9,
      vocabulary = listOf(
        itemMap["w3_jiaren"]!!,
        itemMap["w3_shei"]!!,
        itemMap["w3_you"]!!,
        itemMap["w3_nali"]!!,
        itemMap["w3_piaoliang"]!!,
        itemMap["w3_jia"]!!
      ),
      microLessons = node9Lessons
    )
  )

  fun getNodeCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    val course = world3NodeCourses.find { it.nodeId == nodeId } ?: return null
    return if (learnerName.isNotBlank()) course.interpolateLearner(learnerName) else course
  }
}
