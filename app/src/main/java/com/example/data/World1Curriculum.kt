package com.example.data

import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.model.PandaEmotion
import com.example.model.SkillType
import com.example.model.interpolateLearner

object World1Curriculum {

  // -------------------------------------------------------------
  // ALL WORLD 1 LEARNING ITEMS (TẤT CẢ TỪ VỰNG & MẪU CÂU THẾ GIỚI 1)
  // -------------------------------------------------------------
  val items = listOf(
    // Node 1: Home Base (Trạm xuất phát)
    LearningItem(
      id = "w1_hello",
      hanzi = "你好",
      pinyin = "nǐ hǎo",
      meaning = "Xin chào (Thân mật)",
      vietnameseMeaning = "Xin chào (Thân mật)",
      usageNote = "Dùng để chào hỏi bạn bè, đồng nghiệp, người cùng trang lứa. \"你\" (bạn) + \"好\" (tốt lành).",
      exampleSentence = "你好！我叫 {{learner.name}}。",
      examplePinyin = "nǐ hǎo! wǒ jiào {{learner.name}}.",
      exampleTranslation = "Xin chào! Tôi tên là {{learner.name}}.",
      illustrationType = "hello",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_hello_polite",
      hanzi = "您好",
      pinyin = "nín hǎo",
      meaning = "Kính chào / Chào bác / Thưa thầy",
      vietnameseMeaning = "Kính chào / Chào bác / Thưa thầy",
      usageNote = "Chữ \"您\" thêm bộ Tâm (心) ở dưới chữ \"你\", thể hiện sự tôn trọng và thành kính với người lớn tuổi, thầy cô, đối tác.",
      exampleSentence = "老师，您好！",
      examplePinyin = "lǎoshī, nín hǎo!",
      exampleTranslation = "Thầy/Cô ơi, em kính chào Thầy/Cô!",
      illustrationType = "hello_polite",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_goodbye",
      hanzi = "再见",
      pinyin = "zàijiàn",
      meaning = "Tạm biệt / Hẹn gặp lại",
      vietnameseMeaning = "Tạm biệt / Hẹn gặp lại",
      usageNote = "\"再\" (lại, lần nữa) + \"见\" (gặp mặt). Lời chúc lịch sự hẹn gặp lại nhau sớm.",
      exampleSentence = "明天再见！",
      examplePinyin = "míngtiān zàijiàn!",
      exampleTranslation = "Hẹn ngày mai gặp lại!",
      illustrationType = "goodbye",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_thankyou",
      hanzi = "谢谢",
      pinyin = "xièxiè",
      meaning = "Cảm ơn bạn",
      vietnameseMeaning = "Cảm ơn bạn",
      usageNote = "Có bộ Ngôn (讠) biểu thị lời nói tri ân chân thành, được sử dụng trong mọi tình huống khi nhận sự giúp đỡ.",
      exampleSentence = "谢谢你的帮助！",
      examplePinyin = "xièxiè nǐ de bāngzhù!",
      exampleTranslation = "Cảm ơn sự giúp đỡ của bạn!",
      illustrationType = "thankyou",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_welcome",
      hanzi = "不客气",
      pinyin = "bú kèqi",
      meaning = "Không có chi / Đừng khách sáo",
      vietnameseMeaning = "Không có chi / Đừng khách sáo",
      usageNote = "\"不\" (không) + \"客气\" (khách sáo, câu nệ). Là câu đáp lại lịch sự và ấm áp nhất khi ai đó cảm ơn bạn.",
      exampleSentence = "不客气，这是我应该做的。",
      examplePinyin = "bú kèqi, zhè shì wǒ yīnggāi zuò de.",
      exampleTranslation = "Không có chi, đây là việc tôi nên làm.",
      illustrationType = "welcome",
      category = "greeting"
    ),

    // Node 2: Hello Gate (Cổng Chào Hỏi theo buổi)
    LearningItem(
      id = "w1_morning",
      hanzi = "早上好",
      pinyin = "zǎoshang hǎo",
      meaning = "Chào buổi sáng",
      vietnameseMeaning = "Chào buổi sáng",
      usageNote = "\"早上\" (buổi sáng sớm) + \"好\" (tốt lành). Dùng chào hỏi lúc mặt trời mới mọc.",
      exampleSentence = "早上好！今天天气真好。",
      examplePinyin = "zǎoshang hǎo! jīntiān tiānqì zhēn hǎo.",
      exampleTranslation = "Chào buổi sáng! Hôm nay thời tiết thật đẹp.",
      illustrationType = "morning",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_afternoon",
      hanzi = "下午好",
      pinyin = "xiàwǔ hǎo",
      meaning = "Chào buổi chiều",
      vietnameseMeaning = "Chào buổi chiều",
      usageNote = "\"下午\" (buổi chiều) + \"好\" (tốt lành). Dùng chào từ sau 12h trưa đến chiều tối.",
      exampleSentence = "大家下午好！",
      examplePinyin = "dàjiā xiàwǔ hǎo!",
      exampleTranslation = "Chào buổi chiều cả nhà!",
      illustrationType = "afternoon",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_evening",
      hanzi = "晚上好",
      pinyin = "wǎnshang hǎo",
      meaning = "Chào buổi tối",
      vietnameseMeaning = "Chào buổi tối",
      usageNote = "\"晚上\" (buổi tối) + \"好\". Lời chào ấm cúng khi gặp nhau vào ban đêm.",
      exampleSentence = "晚上好，吃了晚饭吗？",
      examplePinyin = "wǎnshang hǎo, chī le wǎnfàn ma?",
      exampleTranslation = "Chào buổi tối, bạn đã ăn cơm tối chưa?",
      illustrationType = "evening",
      category = "greeting"
    ),
    LearningItem(
      id = "w1_tomorrow",
      hanzi = "明天见",
      pinyin = "míngtiān jiàn",
      meaning = "Ngày mai gặp lại",
      vietnameseMeaning = "Ngày mai gặp lại",
      usageNote = "\"明天\" (ngày mai) + \"见\" (gặp). Lời hẹn tạm biệt khi chuẩn bị gặp lại vào hôm sau.",
      exampleSentence = "下班了，明天见！",
      examplePinyin = "xiàbān le, míngtiān jiàn!",
      exampleTranslation = "Tan làm rồi, mai gặp lại nhé!",
      illustrationType = "tomorrow",
      category = "greeting"
    ),

    // Node 3: Mandarin Tones (Thanh điệu tiếng Trung)
    LearningItem("w1_tone1_ma", "妈", "mā", "Mẹ (Thanh 1 - Ngang cao 55)", category = "tone", toneNumber = 1),
    LearningItem("w1_tone2_ma", "麻", "má", "Cây gai / Tê bì (Thanh 2 - Đi lên 35)", category = "tone", toneNumber = 2),
    LearningItem("w1_tone3_ma", "马", "mǎ", "Con ngựa (Thanh 3 - Uốn lượn 214)", category = "tone", toneNumber = 3),
    LearningItem("w1_tone4_ma", "骂", "mà", "Mắng / Trách móc (Thanh 4 - Giảm dứt khoát 51)", category = "tone", toneNumber = 4),

    // Node 4 & 5: Panda Friend & My Name (Đại từ & Giới thiệu tên)
    LearningItem("w1_pronoun_i", "我", "wǒ", "Tôi / Mình / Em", category = "pronoun"),
    LearningItem("w1_pronoun_you", "你", "nǐ", "Bạn / Cậu / Anh", category = "pronoun"),
    LearningItem("w1_pronoun_he", "他", "tā", "Anh ấy / Ông ấy", category = "pronoun"),
    LearningItem("w1_pronoun_she", "她", "tā", "Cô ấy / Bà ấy", category = "pronoun"),
    LearningItem("w1_verb_call", "叫", "jiào", "Tên là / Gọi là", category = "verb"),
    LearningItem("w1_what", "什么", "shénme", "Gì / Cái gì", category = "question"),
    LearningItem("w1_name", "名字", "míngzi", "Tên / Danh xưng", category = "noun"),
    LearningItem("w1_my", "我的", "wǒ de", "Của tôi", category = "pronoun"),
    LearningItem("w1_your", "你的", "nǐ de", "Của bạn", category = "pronoun"),
    LearningItem("w1_alex", "{{learner.name}}", "{{learner.name}}", "Tên của bạn (Tên riêng)", category = "noun"),

    // Node 6: My Country (Quốc gia & Quốc tịch)
    LearningItem("w1_country", "国家", "guójiā", "Quốc gia / Đất nước", category = "noun"),
    LearningItem("w1_china", "中国", "Zhōngguó", "Trung Quốc", category = "country"),
    LearningItem("w1_vietnam", "越南", "Yuènán", "Việt Nam", category = "country"),
    LearningItem("w1_usa", "美国", "Měiguó", "Nước Mỹ / Hoa Kỳ", category = "country"),
    LearningItem("w1_uk", "英国", "Yīngguó", "Nước Anh", category = "country"),
    LearningItem("w1_japan", "日本", "Rìběn", "Nhật Bản", category = "country"),
    LearningItem("w1_korea", "韩国", "Hánguó", "Hàn Quốc", category = "country"),
    LearningItem("w1_person", "人", "rén", "Người", category = "noun"),
    LearningItem("w1_verb_be", "是", "shì", "Là (Động từ to be)", category = "verb"),
    LearningItem("w1_which", "哪", "nǎ", "Nào / Đâu", category = "question"),

    // Node 7: Numbers (Đếm số 1 đến 10)
    LearningItem("w1_num_1", "一", "yī", "Số một (1)", category = "number"),
    LearningItem("w1_num_2", "二", "èr", "Số hai (2)", category = "number"),
    LearningItem("w1_num_3", "三", "sān", "Số ba (3)", category = "number"),
    LearningItem("w1_num_4", "四", "sì", "Số bốn (4)", category = "number"),
    LearningItem("w1_num_5", "五", "wǔ", "Số năm (5)", category = "number"),
    LearningItem("w1_num_6", "六", "liù", "Số sáu (6)", category = "number"),
    LearningItem("w1_num_7", "七", "qī", "Số bảy (7)", category = "number"),
    LearningItem("w1_num_8", "八", "bā", "Số tám (8)", category = "number"),
    LearningItem("w1_num_9", "九", "jiǔ", "Số chín (9)", category = "number"),
    LearningItem("w1_num_10", "十", "shí", "Số mười (10)", category = "number"),

    // Node 8 & 9: First Conversation (Hội thoại & Giao tiếp)
    LearningItem("w1_happy", "很高兴", "hěn gāoxìng", "Rất vui mừng / Hân hạnh", category = "phrase"),
    LearningItem("w1_meet", "认识", "rènshi", "Làm quen / Quen biết", category = "verb"),
    LearningItem("w1_also", "也", "yě", "Cũng / Cũng vậy", category = "adverb")
  )

  val itemMap = items.associateBy { it.id }

  // -------------------------------------------------------------
  // NODE 1: HOME BASE (出发 - XUẤT PHÁT)
  // -------------------------------------------------------------
  private val node1Lessons = listOf(
    MicroLesson(
      id = "w1_n1_l1",
      nodeId = "w1_n1",
      title = "Bài 1: Khám phá từ mới",
      subtitle = "Những câu chào hỏi cơ bản đầu tiên",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_hello", "w1_hello_polite", "w1_goodbye", "w1_thankyou", "w1_welcome"),
          prompt = "Chạm vào từng thẻ từ vựng để nghe phát âm chuẩn bản xứ và ghi nhớ các câu chào hỏi lịch sự.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l2",
      nodeId = "w1_n1",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Nhận diện dấu thanh điệu qua âm thanh",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_hello"),
          prompt = "Hãy lắng nghe thật kỹ âm thanh. Phiên âm Pinyin nào khớp với từ bạn vừa nghe?",
          audioText = "你好",
          options = listOf("ní hǎo", "nǐ hǎo", "nì hào", "nǐ hao"),
          correctAnswer = "nǐ hǎo",
          explanation = "你好 phát âm là nǐ hǎo gồm 2 thanh 3 (khi nói liền thanh 3 thứ nhất biến điệu thành thanh 2: ní hǎo)."
        ),
        LearningActivity(
          id = "w1_n1_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_thankyou"),
          prompt = "Lắng nghe phát âm và chọn phiên âm Pinyin đúng:",
          audioText = "谢谢",
          options = listOf("xiéxie", "xīexie", "xièxiè", "shìshie"),
          correctAnswer = "xièxiè",
          explanation = "谢谢 phát âm là xièxiè mang thanh 4 (thanh giảm dứt khoát)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l3",
      nodeId = "w1_n1",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Đối chiếu âm thanh với chữ Hán tương ứng",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_hello"),
          prompt = "Lắng nghe âm thanh và chọn chữ Hán khớp với từ vừa phát:",
          audioText = "你好",
          options = listOf("再见", "谢谢", "不客气", "你好"),
          correctAnswer = "你好",
          explanation = "你好 mang ý nghĩa \"Xin chào\" (nǐ hǎo)."
        ),
        LearningActivity(
          id = "w1_n1_l3_a2",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_welcome"),
          prompt = "Lắng nghe âm thanh và chọn cụm từ chữ Hán chính xác:",
          audioText = "不客气",
          options = listOf("您好", "不客气", "再见", "你好"),
          correctAnswer = "不客气",
          explanation = "不客气 (bú kèqi) có nghĩa là \"Không có chi / Đừng khách sáo\"."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l4",
      nodeId = "w1_n1",
      title = "Bài 4: Đọc hiểu & Nhận diện mặt chữ",
      subtitle = "Hiểu ý nghĩa từ và ngữ cảnh sử dụng",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_goodbye"),
          prompt = "Từ chữ Hán sau đây có nghĩa tiếng Việt là gì?",
          hanziPrompt = "再见",
          pinyinPrompt = "zàijiàn",
          options = listOf("Xin chào", "Cảm ơn", "Tạm biệt / Hẹn gặp lại", "Làm ơn"),
          correctAnswer = "Tạm biệt / Hẹn gặp lại",
          explanation = "再见 (zàijiàn) ghép từ \"再\" (lại) + \"见\" (gặp) = Tạm biệt, hẹn gặp lại."
        ),
        LearningActivity(
          id = "w1_n1_l4_a2",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_thankyou"),
          prompt = "Chọn từ chữ Hán có nghĩa là \"Cảm ơn\":",
          pinyinPrompt = "xièxiè",
          options = listOf("您好", "再见", "你好", "谢谢"),
          correctAnswer = "谢谢",
          explanation = "谢谢 (xièxiè) nghĩa là Cảm ơn."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l5",
      nodeId = "w1_n1",
      title = "Bài 5: Điền từ vào chỗ trống",
      subtitle = "Hoàn thành câu đối đáp xã giao lịch sự",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_welcome", "w1_thankyou"),
          prompt = "Khi ai đó nói \"谢谢！\" (Cảm ơn bạn!), câu đáp lại lịch sự nhất là gì?",
          hanziPrompt = "A: 谢谢！\nB: ___ ！",
          options = listOf("再见", "不客气", "你好", "您好"),
          correctAnswer = "不客气",
          explanation = "Khi người khác nói 谢谢 (Cảm ơn), câu đáp lại lịch sự chuẩn mực là 不客气 (Không có chi)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l6",
      nodeId = "w1_n1",
      title = "Bài 6: Hội thoại cùng Gấu DUDU",
      subtitle = "Thực hành phản xạ giao tiếp tình huống",
      type = "conversation",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l6_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_hello"),
          prompt = "Gấu DUDU vẫy tay chào bạn trên đường: Hãy chọn câu đáp lại thân thiện!",
          pandaDialogue = "你好！欢迎来到中国！",
          audioText = "你好！欢迎来到中国！",
          pandaEmotion = PandaEmotion.HAPPY,
          options = listOf("再见！", "不客气！", "你好！"),
          correctAnswer = "你好！",
          explanation = "Đáp lại lời chào \"你好！\" (Xin chào!) bằng câu \"你好！\"."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l7",
      nodeId = "w1_n1",
      title = "Bài 7: Luyện nói phát âm chuẩn",
      subtitle = "Thu âm và chấm điểm giọng đọc trực tiếp",
      type = "speaking",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l7_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_hello"),
          prompt = "Nhấn vào micro và nói to câu \"Xin chào\" bằng tiếng Trung:",
          hanziPrompt = "你好",
          pinyinPrompt = "nǐ hǎo",
          audioText = "你好",
          correctAnswer = "你好"
        ),
        LearningActivity(
          id = "w1_n1_l7_a2",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_thankyou"),
          prompt = "Nói câu \"Cảm ơn bạn\" rõ ràng vào micro:",
          hanziPrompt = "谢谢",
          pinyinPrompt = "xièxiè",
          audioText = "谢谢",
          correctAnswer = "谢谢"
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l8",
      nodeId = "w1_n1",
      title = "Bài 8: Luyện viết & Nhận diện chữ",
      subtitle = "Ghi nhớ cấu trúc mặt chữ Hán",
      type = "writing",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l8_a1",
          type = ActivityType.WRITING,
          skill = SkillType.WRITING,
          itemIds = listOf("w1_hello"),
          prompt = "Chọn đúng chữ Hán tương ứng với phiên âm: nǐ hǎo (Xin chào)",
          pinyinPrompt = "nǐ hǎo",
          options = listOf("您好", "你好", "再见", "谢谢"),
          correctAnswer = "你好",
          explanation = "nǐ hǎo được viết là 你好."
        )
      )
    ),
    MicroLesson(
      id = "w1_n1_l9",
      nodeId = "w1_n1",
      title = "Bài 9: Ôn tập tổng hợp Trạm 1",
      subtitle = "Kiểm tra toàn diện 4 kỹ năng",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n1_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_hello_polite"),
          prompt = "Khi chào thầy cô giáo hoặc người lớn tuổi, ta nên dùng từ nào thể hiện sự kính trọng nhất?",
          options = listOf("你好", "再见", "您好", "不客气"),
          correctAnswer = "您好",
          explanation = "您好 (nín hǎo) dùng chữ \"您\" với bộ Tâm bên dưới để bày tỏ sự tôn kính trang trọng."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 2: HELLO GATE (问候 - CỔNG CHÀO HỎI THEO BUỔI)
  // -------------------------------------------------------------
  private val node2Lessons = listOf(
    MicroLesson(
      id = "w1_n2_l1",
      nodeId = "w1_n2",
      title = "Bài 1: Khám phá lời chào theo thời gian",
      subtitle = "Chào buổi sáng, chiều, tối và hẹn ngày mai",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_morning", "w1_afternoon", "w1_evening", "w1_tomorrow"),
          prompt = "Chạm vào từng thẻ để lắng nghe cách người bản xứ chào nhau theo từng thời điểm trong ngày.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l2",
      nodeId = "w1_n2",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Luyện tai phân biệt các buổi trong ngày",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_morning"),
          prompt = "Lắng nghe âm thanh. Phiên âm nào đúng với câu chào bạn vừa nghe?",
          audioText = "早上好",
          options = listOf("xiàwǔ hǎo", "zǎoshang hǎo", "wǎnshang hǎo", "míngtiān jiàn"),
          correctAnswer = "zǎoshang hǎo",
          explanation = "早上好 phát âm là zǎoshang hǎo có nghĩa là Chào buổi sáng."
        ),
        LearningActivity(
          id = "w1_n2_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_tomorrow"),
          prompt = "Lắng nghe và chọn phiên âm Pinyin của câu hẹn gặp ngày mai:",
          audioText = "明天见",
          options = listOf("zàijiàn", "xiàwǔ hǎo", "míngtiān jiàn", "wǎnshang hǎo"),
          correctAnswer = "míngtiān jiàn",
          explanation = "明天见 là míngtiān jiàn (Ngày mai gặp lại)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l3",
      nodeId = "w1_n2",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Nhận diện mặt chữ theo ngữ cảnh thời gian",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_evening"),
          prompt = "Lắng nghe âm thanh và chọn đúng câu chữ Hán chào buổi tối:",
          audioText = "晚上好",
          options = listOf("早上好", "下午好", "再见", "晚上好"),
          correctAnswer = "晚上好",
          explanation = "晚上好 (wǎnshang hǎo) = Chào buổi tối."
        ),
        LearningActivity(
          id = "w1_n2_l3_a2",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_afternoon"),
          prompt = "Lắng nghe âm thanh và chọn câu chào buổi chiều:",
          audioText = "下午好",
          options = listOf("早上好", "下午好", "晚上好", "明天见"),
          correctAnswer = "下午好",
          explanation = "下午好 (xiàwǔ hǎo) = Chào buổi chiều."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l4",
      nodeId = "w1_n2",
      title = "Bài 4: Đọc hiểu & Ngữ cảnh thời gian",
      subtitle = "Phân biệt buổi sáng, chiều, tối",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_morning"),
          prompt = "Khi gặp đồng nghiệp vào lúc 8 giờ sáng, câu chào phù hợp nhất là gì?",
          options = listOf("下午好", "晚上好", "早上好", "明天见"),
          correctAnswer = "早上好",
          explanation = "8 giờ sáng dùng 早上好 (zǎoshang hǎo - Chào buổi sáng)."
        ),
        LearningActivity(
          id = "w1_n2_l4_a2",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_tomorrow"),
          prompt = "Câu nào sau đây mang ý nghĩa \"Hẹn gặp lại vào ngày mai\"?",
          options = listOf("明天见", "再见", "早上好", "谢谢"),
          correctAnswer = "明天见",
          explanation = "明天 (ngày mai) + 见 (gặp) = 明天见 (Ngày mai gặp lại)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l5",
      nodeId = "w1_n2",
      title = "Bài 5: Điền từ vào chỗ trống",
      subtitle = "Hoàn thành các cặp từ ghép thời gian",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_evening"),
          prompt = "Điền từ còn thiếu để tạo thành câu \"Chào buổi tối\": ___ 好！",
          hanziPrompt = "___ 好！ (Chào buổi tối)",
          options = listOf("早上", "下午", "晚上", "明天"),
          correctAnswer = "晚上",
          explanation = "晚上 (buổi tối) + 好 = 晚上好 (Chào buổi tối)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l6",
      nodeId = "w1_n2",
      title = "Bài 6: Ghép câu hoàn chỉnh",
      subtitle = "Sắp xếp trật tự từ đúng",
      type = "sentence_builder",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l6_a1",
          type = ActivityType.SENTENCE_BUILDER,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_morning"),
          prompt = "Sắp xếp các từ sau thành câu \"Chào buổi sáng\":",
          sentenceWords = listOf("好", "早上"),
          targetSentence = "早上 好",
          correctAnswer = "早上 好",
          explanation = "Trong tiếng Trung, danh từ thời gian đứng trước chữ 好: 早上好."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l7",
      nodeId = "w1_n2",
      title = "Bài 7: Hội thoại cùng Gấu BUBU",
      subtitle = "Luyện phản xạ giao tiếp theo thời điểm",
      type = "conversation",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l7_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_morning"),
          prompt = "Gấu BUBU cầm tách trà chào bạn lúc bình minh: Hãy chọn câu đáp lại phù hợp!",
          pandaDialogue = "早上好！今天一起加油学习中文吧！",
          audioText = "早上好！今天一起加油学习中文吧！",
          pandaEmotion = PandaEmotion.CHEERING,
          options = listOf("晚上好！", "再见！", "早上好！"),
          correctAnswer = "早上好！",
          explanation = "Đáp lại câu chào buổi sáng bằng 早上好！."
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l8",
      nodeId = "w1_n2",
      title = "Bài 8: Phòng thu âm giọng đọc",
      subtitle = "Phát âm lời chào theo buổi chuẩn xác",
      type = "speaking",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l8_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_morning"),
          prompt = "Nói câu \"Chào buổi sáng\" bằng tiếng Trung vào micro:",
          hanziPrompt = "早上好",
          pinyinPrompt = "zǎoshang hǎo",
          audioText = "早上好",
          correctAnswer = "早上好"
        )
      )
    ),
    MicroLesson(
      id = "w1_n2_l9",
      nodeId = "w1_n2",
      title = "Bài 9: Đấu trường thử thách Trạm 2",
      subtitle = "Tổng kết kiến thức lời chào",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n2_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_tomorrow", "w1_goodbye"),
          prompt = "Khi kết thúc buổi học và hẹn gặp lại bạn bè vào ngày mai, câu nào tự nhiên nhất?",
          options = listOf("明天见", "早上好", "下午好", "谢谢"),
          correctAnswer = "明天见",
          explanation = "明天见 mang nghĩa chính xác: Ngày mai gặp lại."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 3: TONE GAME (声调 - THANH ĐIỆU TIẾNG TRUNG)
  // -------------------------------------------------------------
  private val node3Lessons = listOf(
    MicroLesson(
      id = "w1_n3_l1",
      nodeId = "w1_n3",
      title = "Bài 1: Khám phá 4 thanh điệu chuẩn",
      subtitle = "Đồ thị cao độ âm điệu tiếng Trung",
      type = "tone_discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l1_a1",
          type = ActivityType.TONE_DISCOVER,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone1_ma", "w1_tone2_ma", "w1_tone3_ma", "w1_tone4_ma"),
          prompt = "Lắng nghe cao độ thanh điệu thay đổi ý nghĩa từ: mā (Thanh 1: ngang cao ˉ), má (Thanh 2: đi lên ˊ), mǎ (Thanh 3: uốn lượn ˇ), mà (Thanh 4: giảm dứt khoát ˋ).",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l2",
      nodeId = "w1_n3",
      title = "Bài 2: Nghe → Nhận diện thanh điệu",
      subtitle = "Trò chơi bắt thanh điệu chuẩn xác",
      type = "tone_listen",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l2_a1",
          type = ActivityType.TONE_LISTEN,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone1_ma"),
          prompt = "Lắng nghe thật kỹ âm thanh. Đây là thanh điệu thứ mấy trong tiếng Trung?",
          audioText = "mā",
          options = listOf("Thanh 2 (Sắc đi lên ˊ)", "Thanh 1 (Ngang cao ˉ)", "Thanh 3 (Hỏi uốn lượn ˇ)", "Thanh 4 (Huyền dứt khoát ˋ)"),
          correctAnswer = "Thanh 1 (Ngang cao ˉ)",
          explanation = "mā là Thanh 1: âm cao, phẳng và ngân đều (cao độ 55)."
        ),
        LearningActivity(
          id = "w1_n3_l2_a2",
          type = ActivityType.TONE_LISTEN,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone4_ma"),
          prompt = "Lắng nghe âm thanh dứt khoát, rơi nhanh từ cao xuống thấp. Đây là thanh mấy?",
          audioText = "mà",
          options = listOf("Thanh 1 (Ngang cao ˉ)", "Thanh 2 (Sắc đi lên ˊ)", "Thanh 3 (Hỏi uốn lượn ˇ)", "Thanh 4 (Huyền dứt khoát ˋ)"),
          correctAnswer = "Thanh 4 (Huyền dứt khoát ˋ)",
          explanation = "mà rơi dứt khoát từ cao 5 xuống thấp 1 (cao độ 51), đọc mạnh và ngắn."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l3",
      nodeId = "w1_n3",
      title = "Bài 3: Đường cong thanh điệu trực quan",
      subtitle = "Ghép hình dạng cao độ với âm thanh",
      type = "tone_listen",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l3_a1",
          type = ActivityType.TONE_LISTEN,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone2_ma"),
          prompt = "Âm thanh vừa nghe: má. Đường cong cao độ nào biểu diễn âm đi lên này?",
          audioText = "má",
          options = listOf("Đường thẳng ngang cao ( — )", "Đường dốc đi lên ( / )", "Đường thung lũng uốn lượn ( V )", "Đường dốc rơi thẳng ( \\\\ )"),
          correctAnswer = "Đường dốc đi lên ( / )",
          explanation = "Thanh 2 đi từ trung bình lên cao (35), giống ngữ điệu hỏi \"Hả? / Cái gì?\" trong tiếng Việt."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l4",
      nodeId = "w1_n3",
      title = "Bài 4: Chữ Hán → Chọn đúng thanh điệu",
      subtitle = "Mẹ (妈) vs Con Ngựa (马) vs Mắng (骂)",
      type = "multiple_choice",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l4_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone3_ma"),
          prompt = "Chữ Hán \"马\" (Con ngựa) đọc với thanh điệu nào?",
          hanziPrompt = "马",
          options = listOf("mā (Thanh 1)", "má (Thanh 2)", "mǎ (Thanh 3)", "mà (Thanh 4)"),
          correctAnswer = "mǎ (Thanh 3)",
          explanation = "马 (Con ngựa) phát âm là mǎ với Thanh 3 (uốn lượn)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l5",
      nodeId = "w1_n3",
      title = "Bài 5: Điền dấu thanh điệu",
      subtitle = "Phân biệt 4 sắc thái ý nghĩa của âm ma",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone1_ma"),
          prompt = "Từ \"妈\" (Mẹ) mang dấu thanh điệu nào trên chữ a?",
          hanziPrompt = "妈 (Mẹ) = m___",
          options = listOf("á", "ā", "ǎ", "à"),
          correctAnswer = "ā",
          explanation = "妈 (Mẹ) là thanh 1 nên viết dấu ngang trên nguyên âm: mā."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l6",
      nodeId = "w1_n3",
      title = "Bài 6: Đố vui thanh điệu cùng DUDU & BUBU",
      subtitle = "Luyện phản xạ nghe và phân tích cao độ",
      type = "conversation",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l6_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone3_ma"),
          prompt = "DUDU phát âm \"mǎ\". Chữ này có nghĩa là con vật nào?",
          pandaDialogue = "听我说：mǎ！猜猜是什么意思？",
          audioText = "mǎ",
          pandaEmotion = PandaEmotion.THINKING,
          options = listOf("Người mẹ", "Con ngựa", "Cây gai"),
          correctAnswer = "Con ngựa",
          explanation = "mǎ (Thanh 3 - 马) có nghĩa là Con ngựa."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l7",
      nodeId = "w1_n3",
      title = "Bài 7: Luyện phát âm 4 thanh điệu",
      subtitle = "Tập giữ đúng cao độ giọng đọc",
      type = "tone_speaking",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l7_a1",
          type = ActivityType.TONE_SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_tone1_ma"),
          prompt = "Đọc to âm \"mā\" với cao độ phẳng và đều:",
          hanziPrompt = "妈",
          pinyinPrompt = "mā",
          audioText = "妈",
          correctAnswer = "mā"
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l8",
      nodeId = "w1_n3",
      title = "Bài 8: Viết ký hiệu thanh điệu",
      subtitle = "Nhớ vị trí đặt dấu Pinyin",
      type = "writing",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l8_a1",
          type = ActivityType.WRITING,
          skill = SkillType.WRITING,
          itemIds = listOf("w1_tone4_ma"),
          prompt = "Chọn cách viết Pinyin đúng của chữ \"骂\" (Mắng):",
          pinyinPrompt = "mà",
          options = listOf("mā", "má", "mǎ", "mà"),
          correctAnswer = "mà",
          explanation = "骂 mang thanh 4 nên Pinyin viết là mà."
        )
      )
    ),
    MicroLesson(
      id = "w1_n3_l9",
      nodeId = "w1_n3",
      title = "Bài 9: Đấu trường Thanh Điệu Đại Sư",
      subtitle = "Thử thách nhận diện cả 4 thanh",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n3_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.PRONUNCIATION,
          itemIds = listOf("w1_tone2_ma"),
          prompt = "Thanh điệu nào trong tiếng Trung có quy luật cao độ đi từ dưới lên trên (35)?",
          options = listOf("Thanh 1", "Thanh 3", "Thanh 2", "Thanh 4"),
          correctAnswer = "Thanh 2",
          explanation = "Thanh 2 (dấu sắc) là thanh đi lên, cao độ từ 3 lên 5."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 4: PANDA FRIEND (认识你 - GẶP GỠ BẠN GẤU DUDU & BUBU)
  // -------------------------------------------------------------
  private val node4Lessons = listOf(
    MicroLesson(
      id = "w1_n4_l1",
      nodeId = "w1_n4",
      title = "Bài 1: Khám phá đại từ nhân xưng",
      subtitle = "Tôi (我), Bạn (你), Anh ấy (他), Cô ấy (她)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_pronoun_i", "w1_pronoun_you", "w1_pronoun_he", "w1_pronoun_she"),
          prompt = "Chạm vào từng thẻ đại từ để làm quen với cách xưng hô ngôi thứ nhất, thứ hai và thứ ba trong tiếng Trung.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l2",
      nodeId = "w1_n4",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Phân biệt âm wǒ, nǐ và tā",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_pronoun_i"),
          prompt = "Lắng nghe âm thanh và chọn đúng phiên âm Pinyin:",
          audioText = "我",
          options = listOf("nǐ", "tā", "wǒ", "hǎo"),
          correctAnswer = "wǒ",
          explanation = "我 (Tôi / Mình) có Pinyin là wǒ."
        ),
        LearningActivity(
          id = "w1_n4_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_pronoun_she"),
          prompt = "Lắng nghe âm thanh và chọn phiên âm của đại từ:",
          audioText = "她",
          options = listOf("tā", "wǒ", "nǐ", "mā"),
          correctAnswer = "tā",
          explanation = "她 (Cô ấy) có Pinyin là tā (thanh 1)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l3",
      nodeId = "w1_n4",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Phân biệt bộ Nhân đứng (亻) và bộ Nữ (女)",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_pronoun_you"),
          prompt = "Lắng nghe âm thanh và chọn chữ Hán có nghĩa là \"Bạn\":",
          audioText = "你",
          options = listOf("我", "他", "她", "你"),
          correctAnswer = "你",
          explanation = "你 (nǐ) nghĩa là Bạn / Cậu."
        ),
        LearningActivity(
          id = "w1_n4_l3_a2",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_pronoun_she"),
          prompt = "Chọn chữ Hán có bộ Nữ biểu thị \"Cô ấy\":",
          audioText = "她",
          options = listOf("他", "她", "我", "你"),
          correctAnswer = "她",
          explanation = "Chữ \"她\" có bộ Nữ (女) bên trái biểu thị giới tính nữ (Cô ấy)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l4",
      nodeId = "w1_n4",
      title = "Bài 4: Đọc hiểu đại từ nhân xưng",
      subtitle = "Xác định đúng ngôi xưng trong câu",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_pronoun_i"),
          prompt = "Chữ Hán \"我\" trong tiếng Trung mang ý nghĩa gì?",
          hanziPrompt = "我",
          pinyinPrompt = "wǒ",
          options = listOf("Bạn / Cậu", "Tôi / Bản thân tôi", "Anh ấy", "Cô ấy"),
          correctAnswer = "Tôi / Bản thân tôi",
          explanation = "我 (wǒ) là đại từ nhân xưng ngôi thứ nhất: Tôi / Mình / Em."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l5",
      nodeId = "w1_n4",
      title = "Bài 5: Điền từ vào câu",
      subtitle = "Thực hành cấu trúc câu chào hỏi đại từ",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_pronoun_you", "w1_hello"),
          prompt = "Điền chữ còn thiếu để tạo thành câu chào \"Xin chào\": ___ 好！",
          hanziPrompt = "___ 好！",
          options = listOf("我", "她", "他", "你"),
          correctAnswer = "你",
          explanation = "你 (Bạn) + 好 (Tốt) = 你好 (Xin chào bạn)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l6",
      nodeId = "w1_n4",
      title = "Bài 6: Trò chuyện phân vai cùng DUDU",
      subtitle = "Nhận diện đại từ qua ngữ cảnh",
      type = "conversation",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l6_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_pronoun_he"),
          prompt = "DUDU chỉ vào BUBU và giới thiệu: Hãy chọn đại từ phù hợp!",
          pandaDialogue = "这是 BUBU，___ 是我的好朋友！",
          audioText = "这是 BUBU，他是我的好朋友！",
          pandaEmotion = PandaEmotion.CHEERING,
          options = listOf("我", "他", "你"),
          correctAnswer = "他",
          explanation = "Giới thiệu về người thứ ba dùng \"他\" (Anh ấy / Cậu ấy)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l7",
      nodeId = "w1_n4",
      title = "Bài 7: Luyện nói đại từ nhân xưng",
      subtitle = "Phát âm dứt khoát và chuẩn thanh điệu",
      type = "speaking",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l7_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_pronoun_i"),
          prompt = "Nhấn micro và đọc to chữ \"Tôi\" (wǒ) bằng tiếng Trung:",
          hanziPrompt = "我",
          pinyinPrompt = "wǒ",
          audioText = "我",
          correctAnswer = "我"
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l8",
      nodeId = "w1_n4",
      title = "Bài 8: Phân biệt chữ viết 他 và 她",
      subtitle = "Ghi nhớ bộ thủ chữ Hán",
      type = "writing",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l8_a1",
          type = ActivityType.WRITING,
          skill = SkillType.WRITING,
          itemIds = listOf("w1_pronoun_he"),
          prompt = "Chọn chữ Hán có nghĩa là \"Anh ấy / Ông ấy\" (Bộ Nhân đứng 亻):",
          pinyinPrompt = "tā",
          options = listOf("她", "他", "我", "你"),
          correctAnswer = "他",
          explanation = "他 có bộ Nhân đứng biểu thị nam giới (Anh ấy)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n4_l9",
      nodeId = "w1_n4",
      title = "Bài 9: Ôn tập thử thách Trạm 4",
      subtitle = "Làm chủ 4 đại từ nhân xưng cốt lõi",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n4_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_pronoun_i", "w1_pronoun_you"),
          prompt = "Cặp đại từ \"Tôi\" và \"Bạn\" trong tiếng Trung viết là gì?",
          options = listOf("他 - 她", "我 - 你", "你 - 她", "我 - 他"),
          correctAnswer = "我 - 你",
          explanation = "我 (Tôi) và 你 (Bạn)."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 5: MY NAME (我的名字 - TÊN CỦA TÔI)
  // -------------------------------------------------------------
  private val node5Lessons = listOf(
    MicroLesson(
      id = "w1_n5_l1",
      nodeId = "w1_n5",
      title = "Bài 1: Khám phá từ vựng giới thiệu tên",
      subtitle = "Tên gọi (叫), Cái gì (什么), Tên (名字), Của tôi (我的)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_verb_call", "w1_what", "w1_name", "w1_my"),
          prompt = "Chạm vào từng thẻ từ vựng để khám phá cấu trúc câu hỏi và trả lời tên trong tiếng Trung.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l2",
      nodeId = "w1_n5",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Luyện nghe từ jiào, shénme, míngzi",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_name"),
          prompt = "Lắng nghe âm thanh. Phiên âm nào đúng với từ bạn vừa nghe?",
          audioText = "名字",
          options = listOf("shénme", "míngzi", "jiào", "wǒ de"),
          correctAnswer = "míngzi",
          explanation = "名字 (Tên) có Pinyin là míngzi."
        ),
        LearningActivity(
          id = "w1_n5_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_what"),
          prompt = "Lắng nghe từ để hỏi và chọn phiên âm đúng:",
          audioText = "什么",
          options = listOf("shénme", "zěnme", "nǎlǐ", "shéi"),
          correctAnswer = "shénme",
          explanation = "什么 (Cái gì / Gì) phát âm là shénme."
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l3",
      nodeId = "w1_n5",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Nhận diện từ trong câu giới thiệu",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_verb_call"),
          prompt = "Lắng nghe âm thanh và chọn chữ Hán có nghĩa là \"Tên là / Gọi là\":",
          audioText = "叫",
          options = listOf("是", "叫", "在", "有"),
          correctAnswer = "叫",
          explanation = "叫 (jiào) có nghĩa là tên là, gọi là."
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l4",
      nodeId = "w1_n5",
      title = "Bài 4: Đọc hiểu mẫu câu hỏi tên",
      subtitle = "Cấu trúc: 你叫什么名字？",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_pronoun_you", "w1_verb_call", "w1_what", "w1_name"),
          prompt = "Câu tiếng Trung \"你叫什么名字？\" có nghĩa là gì?",
          hanziPrompt = "你叫什么名字？",
          pinyinPrompt = "Nǐ jiào shénme míngzi?",
          options = listOf("Bạn bao nhiêu tuổi?", "Bạn đến từ đâu?", "Bạn tên là gì?", "Bạn khỏe không?"),
          correctAnswer = "Bạn tên là gì?",
          explanation = "你 (Bạn) + 叫 (tên là) + 什么 (gì) + 名字 (tên) = Bạn tên là gì?"
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l5",
      nodeId = "w1_n5",
      title = "Bài 5: Điền từ vào chỗ trống",
      subtitle = "Mẫu câu trả lời: 我叫...",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_verb_call", "w1_alex"),
          prompt = "Điền động từ còn thiếu để hoàn chỉnh câu \"Tôi tên là {{learner.name}}\": 我 ___ {{learner.name}}.",
          hanziPrompt = "我 ___ {{learner.name}}.",
          options = listOf("是", "在", "叫", "有"),
          correctAnswer = "叫",
          explanation = "Mẫu câu giới thiệu tên chuẩn: 我叫 + [Tên]."
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l6",
      nodeId = "w1_n5",
      title = "Bài 6: Ghép câu hoàn chỉnh",
      subtitle = "Xếp từ thành câu giới thiệu bản thân",
      type = "sentence_builder",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l6_a1",
          type = ActivityType.SENTENCE_BUILDER,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_pronoun_i", "w1_verb_call", "w1_alex"),
          prompt = "Sắp xếp các từ sau thành câu \"Tôi tên là {{learner.name}}\":",
          sentenceWords = listOf("{{learner.name}}", "我", "叫"),
          targetSentence = "我 叫 {{learner.name}}",
          correctAnswer = "我 叫 {{learner.name}}",
          explanation = "Trật tự câu chuẩn: Chủ ngữ (我) + Động từ (叫) + Tên ({{learner.name}})."
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l7",
      nodeId = "w1_n5",
      title = "Bài 7: Hỏi đáp tên cùng Gấu BUBU",
      subtitle = "Thực hành đối thoại làm quen",
      type = "conversation",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l7_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_verb_call"),
          prompt = "Gấu BUBU tươi cười hỏi: \"你好！你叫什么名字？\" Hãy chọn câu trả lời đúng của bạn!",
          pandaDialogue = "你好！你叫什么名字？",
          audioText = "你好！你叫什么名字？",
          pandaEmotion = PandaEmotion.HAPPY,
          options = listOf("谢谢！", "我叫 {{learner.name}}。", "再见！"),
          correctAnswer = "我叫 {{learner.name}}。",
          explanation = "Đáp lại câu hỏi tên bằng mẫu \"我叫 + Tên\"."
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l8",
      nodeId = "w1_n5",
      title = "Bài 8: Phòng thu âm - Tự giới thiệu tên",
      subtitle = "Luyện nói trôi chảy câu tên của tôi",
      type = "speaking",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l8_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_pronoun_i", "w1_verb_call", "w1_alex"),
          prompt = "Nói to và rõ ràng câu giới thiệu tên của bạn vào micro:",
          hanziPrompt = "我叫 {{learner.name}}",
          pinyinPrompt = "wǒ jiào {{learner.name}}",
          audioText = "我叫 {{learner.name}}",
          correctAnswer = "我叫 {{learner.name}}"
        )
      )
    ),
    MicroLesson(
      id = "w1_n5_l9",
      nodeId = "w1_n5",
      title = "Bài 9: Đấu trường thử thách Trạm 5",
      subtitle = "Tổng kết mẫu câu hỏi & trả lời tên",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n5_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_what"),
          prompt = "Từ nào mang nghĩa là \"Cái gì\" trong câu hỏi tên?",
          options = listOf("名字", "叫", "什么", "我的"),
          correctAnswer = "什么",
          explanation = "什么 (shénme) là đại từ nghi vấn: Cái gì."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 6: MY COUNTRY (我的国家 - QUỐC GIA CỦA TÔI)
  // -------------------------------------------------------------
  private val node6Lessons = listOf(
    MicroLesson(
      id = "w1_n6_l1",
      nodeId = "w1_n6",
      title = "Bài 1: Khám phá tên các quốc gia & quốc tịch",
      subtitle = "Việt Nam (越南), Trung Quốc (中国), Mỹ (美国), Anh (英国)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_vietnam", "w1_china", "w1_usa", "w1_uk", "w1_japan", "w1_korea", "w1_person", "w1_verb_be"),
          prompt = "Chạm vào từng thẻ quốc gia để học cách nói tên nước và thêm chữ \"人\" (người) để tạo thành quốc tịch.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l2",
      nodeId = "w1_n6",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Phân biệt Yuènán, Zhōngguó, Měiguó",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_vietnam"),
          prompt = "Lắng nghe tên quốc gia. Phiên âm nào đúng với từ bạn vừa nghe?",
          audioText = "越南",
          options = listOf("Zhōngguó", "Yuènán", "Měiguó", "Yīngguó"),
          correctAnswer = "Yuènán",
          explanation = "越南 (Việt Nam) phát âm là Yuènán."
        ),
        LearningActivity(
          id = "w1_n6_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_china"),
          prompt = "Lắng nghe âm thanh và chọn phiên âm của Trung Quốc:",
          audioText = "中国",
          options = listOf("Rìběn", "Hánguó", "Zhōngguó", "Yuènán"),
          correctAnswer = "Zhōngguó",
          explanation = "中国 (Trung Quốc) phát âm là Zhōngguó."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l3",
      nodeId = "w1_n6",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Nhận diện chữ Hán tên các quốc gia",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_vietnam"),
          prompt = "Lắng nghe âm thanh và chọn chữ Hán của \"Việt Nam\":",
          audioText = "越南",
          options = listOf("中国", "美国", "英国", "越南"),
          correctAnswer = "越南",
          explanation = "越南 (Yuènán) = Việt Nam."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l4",
      nodeId = "w1_n6",
      title = "Bài 4: Đọc hiểu cấu trúc câu quốc tịch",
      subtitle = "Mẫu câu: 我是越南人 (Tôi là người Việt Nam)",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_vietnam", "w1_person", "w1_verb_be"),
          prompt = "Câu tiếng Trung \"我是越南人。\" có nghĩa là gì?",
          hanziPrompt = "我是越南人。",
          pinyinPrompt = "Wǒ shì Yuènán rén.",
          options = listOf("Tôi là người Trung Quốc.", "Tôi là người Mỹ.", "Tôi là người Việt Nam.", "Tôi đang ở Việt Nam."),
          correctAnswer = "Tôi là người Việt Nam.",
          explanation = "我 (Tôi) + 是 (là) + 越南 (Việt Nam) + 人 (người) = Tôi là người Việt Nam."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l5",
      nodeId = "w1_n6",
      title = "Bài 5: Điền từ vào chỗ trống",
      subtitle = "Cấu trúc: Tên nước + 人 = Người nước đó",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_person"),
          prompt = "Điền chữ còn thiếu để hoàn thành cụm từ \"Người Trung Quốc\": 中国 ___.",
          hanziPrompt = "中国 ___ (Người Trung Quốc)",
          options = listOf("人", "大", "好", "是"),
          correctAnswer = "人",
          explanation = "Thêm chữ 人 (rén - người) sau tên nước để tạo thành người mang quốc tịch đó: 中国人."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l6",
      nodeId = "w1_n6",
      title = "Bài 6: Ghép câu hoàn chỉnh",
      subtitle = "Sắp xếp câu giới thiệu quốc tịch",
      type = "sentence_builder",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l6_a1",
          type = ActivityType.SENTENCE_BUILDER,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_pronoun_i", "w1_verb_be", "w1_vietnam", "w1_person"),
          prompt = "Sắp xếp các từ sau thành câu \"Tôi là người Việt Nam\":",
          sentenceWords = listOf("越南人", "我", "是"),
          targetSentence = "我 是 越南人",
          correctAnswer = "我 是 越南人",
          explanation = "Cấu trúc: 我 (Chủ ngữ) + 是 (Động từ là) + 越南人 (Quốc tịch)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l7",
      nodeId = "w1_n6",
      title = "Bài 7: Giao lưu quốc tế cùng DUDU",
      subtitle = "Hỏi và đáp về nguồn gốc quốc gia",
      type = "conversation",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l7_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_which", "w1_country", "w1_vietnam"),
          prompt = "DUDU tò mò hỏi bạn: \"你是哪国人？\" (Bạn là người nước nào?). Hãy chọn câu trả lời đúng!",
          pandaDialogue = "你好！你是哪国人？",
          audioText = "你好！你是哪国人？",
          pandaEmotion = PandaEmotion.THINKING,
          options = listOf("我是中国人。", "我叫 {{learner.name}}。", "我是越南人。"),
          correctAnswer = "我是越南人。",
          explanation = "Đáp lại câu hỏi quốc tịch bằng \"我是越南人。\" (Tôi là người Việt Nam)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l8",
      nodeId = "w1_n6",
      title = "Bài 8: Phòng thu âm giọng đọc",
      subtitle = "Tự hào giới thiệu quốc tịch bằng tiếng Trung",
      type = "speaking",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l8_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_pronoun_i", "w1_verb_be", "w1_vietnam", "w1_person"),
          prompt = "Nói to và rõ ràng câu \"Tôi là người Việt Nam\" vào micro:",
          hanziPrompt = "我是越南人",
          pinyinPrompt = "wǒ shì Yuènán rén",
          audioText = "我是越南人",
          correctAnswer = "我是越南人"
        )
      )
    ),
    MicroLesson(
      id = "w1_n6_l9",
      nodeId = "w1_n6",
      title = "Bài 9: Đấu trường kiến thức Trạm 6",
      subtitle = "Tổng kết kiến thức quốc gia & quốc tịch",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n6_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_which"),
          prompt = "Từ nào mang nghĩa là \"Nào / Đâu\" trong câu hỏi \"你是哪国人？\"?",
          options = listOf("是", "哪", "国", "人"),
          correctAnswer = "哪",
          explanation = "哪 (nǎ) là từ để hỏi: Nào / Đâu."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 7: NUMBERS (数字 1-10 - ĐẾM SỐ TỪ 1 ĐẾN 10)
  // -------------------------------------------------------------
  private val node7Lessons = listOf(
    MicroLesson(
      id = "w1_n7_l1",
      nodeId = "w1_n7",
      title = "Bài 1: Khám phá các số đếm từ 1 đến 10",
      subtitle = "Nhận diện mặt chữ Hán 一 (1) đến 十 (10)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_num_1", "w1_num_2", "w1_num_3", "w1_num_4", "w1_num_5", "w1_num_6", "w1_num_7", "w1_num_8", "w1_num_9", "w1_num_10"),
          prompt = "Chạm vào từng thẻ số để lắng nghe cách phát âm và ghi nhớ mặt chữ số tượng hình trong tiếng Trung.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l2",
      nodeId = "w1_n7",
      title = "Bài 2: Nghe → Phiên âm Pinyin các số",
      subtitle = "Luyện phân biệt âm sì (4) và shí (10)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_num_8"),
          prompt = "Lắng nghe âm thanh. Phiên âm nào đúng với số bạn vừa nghe?",
          audioText = "八",
          options = listOf("qī", "bā", "jiǔ", "shí"),
          correctAnswer = "bā",
          explanation = "八 (Số 8) có Pinyin là bā (thanh 1)."
        ),
        LearningActivity(
          id = "w1_n7_l2_a2",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_num_4"),
          prompt = "Lắng nghe và chọn phiên âm của số Bốn (4):",
          audioText = "四",
          options = listOf("sì", "shí", "sān", "èr"),
          correctAnswer = "sì",
          explanation = "四 (Số 4) phát âm là sì (thanh 4, âm thẳng lưỡi)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l3",
      nodeId = "w1_n7",
      title = "Bài 3: Nghe → Chữ Hán các số",
      subtitle = "Đối chiếu nhanh âm thanh và mặt chữ",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_num_6"),
          prompt = "Lắng nghe âm thanh và chọn chữ Hán của số Sáu (6):",
          audioText = "六",
          options = listOf("五", "七", "六", "八"),
          correctAnswer = "六",
          explanation = "六 (liù) là số 6."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l4",
      nodeId = "w1_n7",
      title = "Bài 4: Đọc hiểu & Nhận diện mặt chữ số",
      subtitle = "Tập đếm dãy số liên tục",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_num_1", "w1_num_2", "w1_num_3"),
          prompt = "Dãy chữ Hán \"一 二 三\" đại diện cho các số nào?",
          hanziPrompt = "一 二 三",
          options = listOf("1, 2, 3", "4, 5, 6", "7, 8, 9", "8, 9, 10"),
          correctAnswer = "1, 2, 3",
          explanation = "一 (1), 二 (2), 三 (3)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l5",
      nodeId = "w1_n7",
      title = "Bài 5: Điền số còn thiếu vào chuỗi",
      subtitle = "Thử thách tư duy logic số Hán",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_num_5"),
          prompt = "Điền chữ số còn thiếu vào chuỗi: 三, 四, ___, 六, 七.",
          hanziPrompt = "三, 四, ___, 六, 七.",
          options = listOf("二", "五", "八", "九"),
          correctAnswer = "五",
          explanation = "Số đứng giữa 4 (四) và 6 (六) là 5 (五)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l6",
      nodeId = "w1_n7",
      title = "Bài 6: Trò chơi đếm số cùng Gấu BUBU",
      subtitle = "Đếm số que tre của BUBU",
      type = "conversation",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l6_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_num_10"),
          prompt = "BUBU vui mừng khoe 10 khúc tre: \"你看！我有十根竹子！\" Số 10 đọc là gì?",
          pandaDialogue = "你看！我有十根竹子！",
          audioText = "你看！我有十根竹子！",
          pandaEmotion = PandaEmotion.CHEERING,
          options = listOf("sì", "shí", "bā"),
          correctAnswer = "shí",
          explanation = "十 (Số 10) có Pinyin là shí (âm uốn lưỡi, thanh 2)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l7",
      nodeId = "w1_n7",
      title = "Bài 7: Luyện nói đếm số",
      subtitle = "Luyện phát âm chuẩn số 1 đến 10",
      type = "speaking",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l7_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_num_8"),
          prompt = "Nói số Tám (8) bằng tiếng Trung vào micro:",
          hanziPrompt = "八",
          pinyinPrompt = "bā",
          audioText = "八",
          correctAnswer = "八"
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l8",
      nodeId = "w1_n7",
      title = "Bài 8: Nhận diện chữ viết số Hán",
      subtitle = "Nhớ nét viết các số cơ bản",
      type = "writing",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l8_a1",
          type = ActivityType.WRITING,
          skill = SkillType.WRITING,
          itemIds = listOf("w1_num_10"),
          prompt = "Chữ Hán hình dấu cộng (+) là số mấy?",
          pinyinPrompt = "shí",
          options = listOf("一", "七", "十", "八"),
          correctAnswer = "十",
          explanation = "十 là chữ Hán số 10."
        )
      )
    ),
    MicroLesson(
      id = "w1_n7_l9",
      nodeId = "w1_n7",
      title = "Bài 9: Đấu trường Số Học Thần Tốc",
      subtitle = "Kiểm tra phản xạ nhận diện số 1-10",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n7_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_num_9"),
          prompt = "Số Chín (9) trong tiếng Trung viết là gì?",
          options = listOf("八", "六", "九", "七"),
          correctAnswer = "九",
          explanation = "九 (jiǔ) là số 9."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 8: FIRST CONVERSATION (第一次对话 - HỘI THOẠI ĐẦU TIÊN)
  // -------------------------------------------------------------
  private val node8Lessons = listOf(
    MicroLesson(
      id = "w1_n8_l1",
      nodeId = "w1_n8",
      title = "Bài 1: Khám phá các mẫu câu xã giao",
      subtitle = "Rất vui được làm quen với bạn (很高兴认识你)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l1_a1",
          type = ActivityType.DISCOVER,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_happy", "w1_meet", "w1_also"),
          prompt = "Chạm vào từng thẻ để học mẫu câu thể hiện sự hân hạnh khi kết bạn mới trong tiếng Trung.",
          correctAnswer = "continue"
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l2",
      nodeId = "w1_n8",
      title = "Bài 2: Nghe → Phiên âm Pinyin",
      subtitle = "Luyện nghe câu giao tiếp hoàn chỉnh",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l2_a1",
          type = ActivityType.LISTEN_PINYIN,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_happy", "w1_meet"),
          prompt = "Lắng nghe câu chào xã giao và chọn phiên âm Pinyin đúng:",
          audioText = "很高兴认识你",
          options = listOf("Nǐ jiào shénme míngzi?", "Hěn gāoxìng rènshi nǐ", "Wǒ shì Yuènán rén", "Míngtiān zàijiàn"),
          correctAnswer = "Hěn gāoxìng rènshi nǐ",
          explanation = "很高兴认识你 có Pinyin là Hěn gāoxìng rènshi nǐ (Rất vui được quen biết bạn)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l3",
      nodeId = "w1_n8",
      title = "Bài 3: Nghe → Chữ Hán",
      subtitle = "Nhận diện câu đáp lại có từ 也 (Cũng)",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l3_a1",
          type = ActivityType.LISTEN_HANZI,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_also", "w1_happy"),
          prompt = "Lắng nghe âm thanh và chọn câu đáp lại \"Tôi cũng rất vui được làm quen với bạn\":",
          audioText = "我也很高兴认识你",
          options = listOf("很高兴认识你", "我也很高兴认识你", "我是越南人", "谢谢你的帮助"),
          correctAnswer = "我也很高兴认识你",
          explanation = "Chữ \"也\" (yě - cũng) đứng trước tính từ: 我也很高兴认识你."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l4",
      nodeId = "w1_n8",
      title = "Bài 4: Đọc hiểu đoạn hội thoại ngắn",
      subtitle = "Luyện đọc đoạn làm quen hoàn chỉnh",
      type = "reading",
      order = 4,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l4_a1",
          type = ActivityType.READING,
          skill = SkillType.READING,
          itemIds = listOf("w1_happy", "w1_meet"),
          prompt = "Ý nghĩa chính xác của câu \"很高兴认识你！\" là gì?",
          hanziPrompt = "很高兴认识你！",
          options = listOf("Hẹn ngày mai gặp lại!", "Cảm ơn sự giúp đỡ của bạn!", "Rất vui được quen biết bạn!", "Bạn có khỏe không?"),
          correctAnswer = "Rất vui được quen biết bạn!",
          explanation = "很高兴 (Rất vui) + 认识 (Quen biết) + 你 (Bạn)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l5",
      nodeId = "w1_n8",
      title = "Bài 5: Điền từ vào câu đối đáp",
      subtitle = "Sử dụng phó từ 也 (Cũng)",
      type = "fill_blank",
      order = 5,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l5_a1",
          type = ActivityType.FILL_BLANK,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_also"),
          prompt = "Điền từ còn thiếu để tạo thành câu \"Tôi CŨNG rất vui được làm quen với bạn\": 我 ___ 很高兴认识你。",
          hanziPrompt = "A: 很高兴认识你！\nB: 我 ___ 很高兴认识你。",
          options = listOf("是", "叫", "在", "也"),
          correctAnswer = "也",
          explanation = "Dùng phó từ \"也\" (yě - cũng) để đồng tình với câu nói của đối phương."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l6",
      nodeId = "w1_n8",
      title = "Bài 6: Ghép câu hoàn chỉnh",
      subtitle = "Sắp xếp câu xã giao lịch sự",
      type = "sentence_builder",
      order = 6,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l6_a1",
          type = ActivityType.SENTENCE_BUILDER,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_happy", "w1_meet", "w1_pronoun_you"),
          prompt = "Sắp xếp các từ thành câu \"Rất vui được quen biết bạn\":",
          sentenceWords = listOf("认识", "你", "很高兴"),
          targetSentence = "很高兴 认识 你",
          correctAnswer = "很高兴 认识 你",
          explanation = "Trật tự câu: 很高兴 + 认识 + 你."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l7",
      nodeId = "w1_n8",
      title = "Bài 7: Hội thoại đa lượt cùng DUDU & BUBU",
      subtitle = "Mô phỏng cuộc gặp gỡ bạn bè thực tế",
      type = "conversation",
      order = 7,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l7_a1",
          type = ActivityType.PANDA_CONVERSATION,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_happy", "w1_meet"),
          prompt = "DUDU bắt tay bạn nồng nhiệt: \"很高兴认识你！\" Bạn sẽ đáp lại như thế nào?",
          pandaDialogue = "很高兴认识你！",
          audioText = "很高兴认识你！",
          pandaEmotion = PandaEmotion.CHEERING,
          options = listOf("不客气！", "再见！", "我也很高兴认识你！"),
          correctAnswer = "我也很高兴认识你！",
          explanation = "Đáp lại bằng \"我也很高兴认识你！\" (Tôi cũng rất vui được làm quen với bạn)."
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l8",
      nodeId = "w1_n8",
      title = "Bài 8: Phòng thu âm - Luyện nói câu xã giao",
      subtitle = "Phát âm chuẩn ngữ điệu tự nhiên bản xứ",
      type = "speaking",
      order = 8,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l8_a1",
          type = ActivityType.SPEAKING,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_happy", "w1_meet", "w1_pronoun_you"),
          prompt = "Nói câu \"Rất vui được làm quen với bạn\" vào micro:",
          hanziPrompt = "很高兴认识你",
          pinyinPrompt = "hěn gāoxìng rènshi nǐ",
          audioText = "很高兴认识你",
          correctAnswer = "很高兴认识你"
        )
      )
    ),
    MicroLesson(
      id = "w1_n8_l9",
      nodeId = "w1_n8",
      title = "Bài 9: Đấu trường Giao Tiếp Thực Chiến",
      subtitle = "Tổng hợp toàn bộ kỹ năng giao tiếp cơ bản",
      type = "mixed_review",
      order = 9,
      activities = listOf(
        LearningActivity(
          id = "w1_n8_l9_a1",
          type = ActivityType.MULTIPLE_CHOICE,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_also"),
          prompt = "Từ \"也\" (yě) trong câu \"我也很高兴认识你\" có nghĩa là gì?",
          options = listOf("Rất", "Là", "Cũng", "Tốt"),
          correctAnswer = "Cũng",
          explanation = "也 (yě) có nghĩa là \"Cũng / Cũng vậy\"."
        )
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 9: FIRST CONVERSATION BOSS (终极挑战 - ĐẤU TRƯỜNG ĐẠI SƯ VẠN LÝ TRƯỜNG THÀNH)
  // -------------------------------------------------------------
  private val node9Lessons = listOf(
    MicroLesson(
      id = "w1_n9_l1",
      nodeId = "w1_n9",
      title = "Đấu trường Đỉnh Vạn Lý Trường Thành: Thử thách 8 vòng",
      subtitle = "Đại sư Cao - Bài kiểm tra tổng hợp toàn diện World 1",
      type = "boss",
      order = 1,
      activities = listOf(
        // Vòng 1: Chào hỏi mở đầu
        LearningActivity(
          id = "w1_n9_r1",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_hello"),
          prompt = "Đại sư Cao chắp tay chào: \"你好！欢迎来到长城！\" (Xin chào! Chào mừng bạn đến Vạn Lý Trường Thành!). Hãy chọn câu đáp lại trang trọng:",
          pandaDialogue = "你好！欢迎来到长城！",
          audioText = "你好！欢迎来到长城！",
          pandaEmotion = PandaEmotion.NEUTRAL,
          options = listOf("再见！", "不客气！", "你好！", "明天见！"),
          correctAnswer = "你好！",
          explanation = "Chào lại Đại sư bằng \"你好！\".",
          roundNumber = 1
        ),
        // Vòng 2: Giới thiệu tên
        LearningActivity(
          id = "w1_n9_r2",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.GRAMMAR,
          itemIds = listOf("w1_verb_call", "w1_alex"),
          prompt = "Đại sư hỏi: \"你叫什么名字？\" (Bạn tên là gì?). Sắp xếp các từ thành câu trả lời của bạn:",
          sentenceWords = listOf("叫", "我", "{{learner.name}}"),
          targetSentence = "我 叫 {{learner.name}}",
          correctAnswer = "我 叫 {{learner.name}}",
          explanation = "Cấu trúc: 我 叫 {{learner.name}}.",
          roundNumber = 2
        ),
        // Vòng 3: Giới thiệu quốc tịch
        LearningActivity(
          id = "w1_n9_r3",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.VOCABULARY,
          itemIds = listOf("w1_vietnam", "w1_person"),
          prompt = "Đại sư hỏi thăm nguồn gốc: \"你是哪国人？\" (Bạn là người nước nào?):",
          options = listOf("我是中国人。", "我是越南人。", "我的名字叫 {{learner.name}}。", "七八九。"),
          correctAnswer = "我是越南人。",
          explanation = "Trả lời: 我是越南人 (Tôi là người Việt Nam).",
          roundNumber = 3
        ),
        // Vòng 4: Xã giao lịch sự
        LearningActivity(
          id = "w1_n9_r4",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.CONVERSATION,
          itemIds = listOf("w1_happy", "w1_meet"),
          prompt = "Đại sư mỉm cười gật đầu: \"很高兴认识你！\" Hãy đáp lại:",
          options = listOf("谢谢，不客气！", "早上好！", "再见！", "我也很高兴认识你！"),
          correctAnswer = "我也很高兴认识你！",
          explanation = "Đáp lại: 我也很高兴认识你！ (Tôi cũng rất vui được làm quen với Đại sư!).",
          roundNumber = 4
        ),
        // Vòng 5: Luyện nói tự giới thiệu bản thân
        LearningActivity(
          id = "w1_n9_r5",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.SPEAKING,
          itemIds = listOf("w1_hello", "w1_alex", "w1_vietnam"),
          prompt = "Nói câu giới thiệu bản thân hoàn chỉnh với Đại sư: \"你好，我叫 {{learner.name}}。我是越南人。\"",
          hanziPrompt = "你好，我叫 {{learner.name}}。我是越南人。",
          pinyinPrompt = "Nǐ hǎo, wǒ jiào {{learner.name}}. Wǒ shì Yuènán rén.",
          audioText = "你好，我叫 {{learner.name}}。我是越南人。",
          correctAnswer = "你好，我叫 {{learner.name}}。我是越南人。",
          roundNumber = 5
        ),
        // Vòng 6: Luyện nghe hiểu chỉ dẫn
        LearningActivity(
          id = "w1_n9_r6",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.LISTENING,
          itemIds = listOf("w1_morning", "w1_num_8"),
          prompt = "Lắng nghe lời căn dặn của Đại sư về giờ hẹn sáng mai:",
          audioText = "早上好！Chúng ta hẹn gặp lúc 8 giờ!",
          options = listOf("Chào buổi tối! Hẹn ngày mai gặp!", "Xin chào! Bạn tên là gì?", "Chào buổi sáng! Chúng ta hẹn gặp lúc 8 giờ!", "Cảm ơn bạn rất nhiều!"),
          correctAnswer = "Chào buổi sáng! Chúng ta hẹn gặp lúc 8 giờ!",
          explanation = "早上好 (Chào buổi sáng) + 我们八点见 (Chúng ta hẹn gặp lúc 8 giờ).",
          roundNumber = 6
        ),
        // Vòng 7: Đọc hiểu văn bia cổ
        LearningActivity(
          id = "w1_n9_r7",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.READING,
          itemIds = listOf("w1_num_1", "w1_num_10"),
          prompt = "Đọc bia đá cổ trên Trường Thành: \"一二三四五，六七八九十。\"\nVăn bia này đang mô tả điều gì?",
          hanziPrompt = "一二三四五，六七八九十。",
          options = listOf("Đếm các số tự nhiên từ 1 đến 10", "Lời chào buổi sáng và buổi tối", "Danh sách tên các quốc gia", "Câu hỏi tên và tuổi"),
          correctAnswer = "Đếm các số tự nhiên từ 1 đến 10",
          explanation = "一 (1) 二 (2) 三 (3) 四 (4) 五 (5) 六 (6) 七 (7) 八 (8) 九 (9) 十 (10).",
          roundNumber = 7
        ),
        // Vòng 8: Luyện viết câu hoàn chỉnh
        LearningActivity(
          id = "w1_n9_r8",
          type = ActivityType.BOSS_ROUND,
          skill = SkillType.WRITING,
          itemIds = listOf("w1_pronoun_i", "w1_verb_call", "w1_alex"),
          prompt = "Thử thách quyết định cuối cùng: Chọn đúng câu chữ Hán của \"wǒ jiào {{learner.name}}\":",
          pinyinPrompt = "wǒ jiào {{learner.name}}",
          options = listOf("我是越南人", "你好 {{learner.name}}", "我叫 {{learner.name}}", "谢谢 {{learner.name}}"),
          correctAnswer = "我叫 {{learner.name}}",
          explanation = "我叫 {{learner.name}}.",
          roundNumber = 8
        )
      )
    )
  )

  // -------------------------------------------------------------
  // TOÀN BỘ 9 TRẠM BÀI HỌC THẾ GIỚI 1 (VẠN LÝ TRƯỜNG THÀNH)
  // -------------------------------------------------------------
  val world1NodeCourses: List<NodeCourseData> = listOf(
    NodeCourseData(
      nodeId = "w1_n1",
      title = "Home Base",
      subtitle = "出发 • Xuất phát khởi hành",
      description = "Làm quen với các câu chào hỏi căn bản nhất: 你好, 您好, 再见, 谢谢, 不客气.",
      order = 1,
      vocabulary = listOf(
        itemMap["w1_hello"]!!,
        itemMap["w1_hello_polite"]!!,
        itemMap["w1_goodbye"]!!,
        itemMap["w1_thankyou"]!!,
        itemMap["w1_welcome"]!!
      ),
      microLessons = node1Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n2",
      title = "Hello Gate",
      subtitle = "问候 • Cổng Chào Hỏi theo buổi",
      description = "Học các lời chào theo thời điểm trong ngày: 早上好, 下午好, 晚上好, 明天见.",
      order = 2,
      vocabulary = listOf(
        itemMap["w1_morning"]!!,
        itemMap["w1_afternoon"]!!,
        itemMap["w1_evening"]!!,
        itemMap["w1_tomorrow"]!!,
        itemMap["w1_goodbye"]!!
      ),
      microLessons = node2Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n3",
      title = "Mandarin Tones",
      subtitle = "声调 • 4 Thanh Điệu Tiếng Trung",
      description = "Khám phá bản đồ cao độ thanh điệu qua ví dụ kinh điển: mā (mẹ), má (gai), mǎ (ngựa), mà (mắng).",
      order = 3,
      vocabulary = listOf(
        itemMap["w1_tone1_ma"]!!,
        itemMap["w1_tone2_ma"]!!,
        itemMap["w1_tone3_ma"]!!,
        itemMap["w1_tone4_ma"]!!
      ),
      microLessons = node3Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n4",
      title = "Panda Friend",
      subtitle = "朋友 • Gặp gỡ bạn Gấu DUDU & BUBU",
      description = "Làm chủ 4 đại từ nhân xưng cơ bản: 我 (tôi), 你 (bạn), 他 (anh ấy), 她 (cô ấy).",
      order = 4,
      vocabulary = listOf(
        itemMap["w1_pronoun_i"]!!,
        itemMap["w1_pronoun_you"]!!,
        itemMap["w1_pronoun_he"]!!,
        itemMap["w1_pronoun_she"]!!
      ),
      microLessons = node4Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n5",
      title = "My Name",
      subtitle = "名字 • Tên của tôi",
      description = "Cấu trúc câu hỏi tên: 你叫什么名字？ và trả lời tự tin: 我叫...",
      order = 5,
      vocabulary = listOf(
        itemMap["w1_verb_call"]!!,
        itemMap["w1_what"]!!,
        itemMap["w1_name"]!!,
        itemMap["w1_my"]!!
      ),
      microLessons = node5Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n6",
      title = "My Country",
      subtitle = "国家 • Quốc gia & Quốc tịch",
      description = "Tự hào giới thiệu quốc tịch: 越南 (Việt Nam), 中国 (Trung Quốc), 美国 (Mỹ), 英国 (Anh), 人 (người).",
      order = 6,
      vocabulary = listOf(
        itemMap["w1_vietnam"]!!,
        itemMap["w1_china"]!!,
        itemMap["w1_usa"]!!,
        itemMap["w1_uk"]!!,
        itemMap["w1_person"]!!,
        itemMap["w1_verb_be"]!!
      ),
      microLessons = node6Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n7",
      title = "Numbers 1-10",
      subtitle = "数字 • Đếm số 1 đến 10",
      description = "Học thuộc và nhận diện nhanh chữ Hán từ 一 (1) đến 十 (10).",
      order = 7,
      vocabulary = listOf(
        itemMap["w1_num_1"]!!,
        itemMap["w1_num_2"]!!,
        itemMap["w1_num_3"]!!,
        itemMap["w1_num_4"]!!,
        itemMap["w1_num_5"]!!,
        itemMap["w1_num_6"]!!,
        itemMap["w1_num_7"]!!,
        itemMap["w1_num_8"]!!,
        itemMap["w1_num_9"]!!,
        itemMap["w1_num_10"]!!
      ),
      microLessons = node7Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n8",
      title = "First Conversation",
      subtitle = "对话 • Hội thoại hoàn chỉnh đầu tiên",
      description = "Giao tiếp xã giao lịch sự: 很高兴认识你 (Rất vui được quen biết bạn) và 我也很高兴认识你.",
      order = 8,
      vocabulary = listOf(
        itemMap["w1_happy"]!!,
        itemMap["w1_meet"]!!,
        itemMap["w1_also"]!!
      ),
      microLessons = node8Lessons
    ),
    NodeCourseData(
      nodeId = "w1_n9",
      title = "First Conversation Boss",
      subtitle = "终极挑战 • Đấu trường Đại sư",
      description = "Thử thách 8 vòng tổng hợp toàn diện cùng Đại sư Cao trên đỉnh Vạn Lý Trường Thành.",
      order = 9,
      vocabulary = listOf(
        itemMap["w1_hello"]!!,
        itemMap["w1_name"]!!,
        itemMap["w1_vietnam"]!!,
        itemMap["w1_happy"]!!,
        itemMap["w1_meet"]!!,
        itemMap["w1_num_8"]!!
      ),
      microLessons = node9Lessons
    )
  )

  fun getNodeCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    val course = world1NodeCourses.find { it.nodeId == nodeId } ?: return null
    return if (learnerName.isNotBlank()) course.interpolateLearner(learnerName) else course
  }
}
