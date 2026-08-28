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

object World5Curriculum {

  // -------------------------------------------------------------
  // ALL WORLD 5 LEARNING ITEMS
  // -------------------------------------------------------------
  val items = listOf(
    // Node 1: Market Environment
    LearningItem("w5_shichang", "市场", "shìchǎng", "Chợ / Khu chợ", usageNote = "Khu chợ mua bán sầm uất.", exampleSentence = "这是北京市场。", examplePinyin = "zhè shì Běijīng shìchǎng.", exampleTranslation = "Đây là chợ Bắc Kinh.", category = "location"),
    LearningItem("w5_mai_buy", "买", "mǎi", "Mua (Thanh 3)", usageNote = "Mua hàng hóa.", exampleSentence = "我要买东西。", examplePinyin = "wǒ yào mǎi dōngxi.", exampleTranslation = "Tôi muốn mua đồ.", category = "verb"),
    LearningItem("w5_mai_sell", "卖", "mài", "Bán (Thanh 4)", usageNote = "Bán hàng hóa.", exampleSentence = "老板卖水果。", examplePinyin = "lǎobǎn mài shuǐguǒ.", exampleTranslation = "Ông chủ bán hoa quả.", category = "verb"),
    LearningItem("w5_dongxi", "东西", "dōngxi", "Đồ vật / Hàng hóa", usageNote = "Đồ đạc cần mua sắm.", exampleSentence = "我去市场买东西。", examplePinyin = "wǒ qù shìchǎng mǎi dōngxi.", exampleTranslation = "Tôi đi chợ mua đồ.", category = "noun"),
    LearningItem("w5_qian", "钱", "qián", "Tiền", usageNote = "Tiền tệ thanh toán.", exampleSentence = "我有钱。", examplePinyin = "wǒ yǒu qián.", exampleTranslation = "Tôi có tiền.", category = "noun"),
    LearningItem("w5_shangdian", "商店", "shāngdiàn", "Cửa hàng / Quầy hàng", usageNote = "Tiệm buôn bán.", exampleSentence = "商店里有很多东西。", examplePinyin = "shāngdiàn lǐ yǒu hěn duō dōngxi.", exampleTranslation = "Trong cửa hàng có rất nhiều đồ.", category = "location"),
    LearningItem("w5_laoban", "老板", "lǎobǎn", "Ông chủ / Chủ tiệm", usageNote = "Người bán hàng, chủ cửa hàng.", exampleSentence = "老板，你好！", examplePinyin = "lǎobǎn, nǐ hǎo!", exampleTranslation = "Chào ông chủ ạ!", category = "people"),

    // Node 2: I Want to Buy...
    LearningItem("w5_xiang", "想", "xiǎng", "Muốn / Nghĩ (Would like)", usageNote = "Bày tỏ mong muốn, dự định.", exampleSentence = "我想买水。", examplePinyin = "wǒ xiǎng mǎi shuǐ.", exampleTranslation = "Tôi muốn mua nước.", category = "verb"),
    LearningItem("w5_yao", "要", "yào", "Muốn / Cần / Lấy", usageNote = "Muốn lấy hoặc yêu cầu cụ thể.", exampleSentence = "我要买茶。", examplePinyin = "wǒ yào mǎi chá.", exampleTranslation = "Tôi muốn mua trà.", category = "verb"),
    LearningItem("w5_shui", "水", "shuǐ", "Nước / Nước khoáng", usageNote = "Nước uống.", exampleSentence = "我想喝水。", examplePinyin = "wǒ xiǎng hē shuǐ.", exampleTranslation = "Tôi muốn uống nước.", category = "food"),
    LearningItem("w5_cha", "茶", "chá", "Trà / Trà xanh", usageNote = "Thức uống trà truyền thống.", exampleSentence = "我要买茶。", examplePinyin = "wǒ yào mǎi chá.", exampleTranslation = "Tôi muốn mua trà.", category = "food"),
    LearningItem("w5_shuiguo", "水果", "shuǐguǒ", "Hoa quả / Trái cây", usageNote = "Trái cây tươi ngon.", exampleSentence = "我想买水果。", examplePinyin = "wǒ xiǎng mǎi shuǐguǒ.", exampleTranslation = "Tôi muốn mua hoa quả.", category = "food"),
    LearningItem("w5_pingguo", "苹果", "píngguǒ", "Quả táo", usageNote = "Trái táo tươi ngon.", exampleSentence = "我想买苹果。", examplePinyin = "wǒ xiǎng mǎi píngguǒ.", exampleTranslation = "Tôi muốn mua táo.", category = "food"),
    LearningItem("w5_shu", "书", "shū", "Sách", usageNote = "Sách báo tại chợ sách.", exampleSentence = "我想买书。", examplePinyin = "wǒ xiǎng mǎi shū.", exampleTranslation = "Tôi muốn mua sách.", category = "item"),

    // Node 3: How Much? (Prices & Money)
    LearningItem("w5_duoshaoqian", "多少钱", "duōshao qián", "Bao nhiêu tiền?", usageNote = "Mẫu câu hỏi giá phổ biến nhất.", exampleSentence = "这个多少钱？", examplePinyin = "zhège duōshao qián?", exampleTranslation = "Cái này bao nhiêu tiền?", category = "question"),
    LearningItem("w5_duoshao", "多少", "duōshao", "Bao nhiêu", usageNote = "Đại từ nghi vấn hỏi số lượng lớn.", exampleSentence = "你有多少书？", examplePinyin = "nǐ yǒu duōshao shū?", exampleTranslation = "Bạn có bao nhiêu cuốn sách?", category = "question"),
    LearningItem("w5_kuai", "块", "kuài", "Đồng / Khối (Yuan khẩu ngữ)", usageNote = "Đơn vị tiền tệ khẩu ngữ tại Trung Quốc.", exampleSentence = "十块钱。", examplePinyin = "shí kuài qián.", exampleTranslation = "Mười đồng tệ.", category = "money"),
    LearningItem("w5_yuan", "元", "yuán", "Nguyên / Tệ (Văn viết)", usageNote = "Đơn vị tiền chính thức (RMB).", exampleSentence = "二十元。", examplePinyin = "èrshí yuán.", exampleTranslation = "Hai mươi tệ.", category = "money"),
    LearningItem("w5_gui", "贵", "guì", "Đắt / Giá cao", usageNote = "Tính từ chỉ giá đắt đỏ.", exampleSentence = "太贵了！", examplePinyin = "tài guì le!", exampleTranslation = "Đắt quá rồi!", category = "adjective"),
    LearningItem("w5_pianyi", "便宜", "piányi", "Rẻ / Phải chăng", usageNote = "Giá cả rẻ, hợp lý.", exampleSentence = "这个很便宜。", examplePinyin = "zhège hěn piányi.", exampleTranslation = "Cái này rất rẻ.", category = "adjective"),

    // Node 4: I Want This One (Selection)
    LearningItem("w5_zhege", "这个", "zhège", "Cái này / Món này", usageNote = "Chỉ đồ vật ở gần.", exampleSentence = "我要这个。", examplePinyin = "wǒ yào zhège.", exampleTranslation = "Tôi lấy cái này.", category = "pronoun"),
    LearningItem("w5_nage", "那个", "nàge", "Cái kia / Món kia", usageNote = "Chỉ đồ vật ở xa.", exampleSentence = "我要那个。", examplePinyin = "wǒ yào nàge.", exampleTranslation = "Tôi lấy cái kia.", category = "pronoun"),
    LearningItem("w5_buyao", "不要", "bú yào", "Không lấy / Không muốn", usageNote = "Từ chối mua món hàng.", exampleSentence = "我不要这个。", examplePinyin = "wǒ bú yào zhège.", exampleTranslation = "Tôi không lấy cái này.", category = "verb"),
    LearningItem("w5_gei", "给", "gěi", "Đưa cho / Cho", usageNote = "Trao vật cho người khác.", exampleSentence = "给你钱。", examplePinyin = "gěi nǐ qián.", exampleTranslation = "Gửi tiền bạn / Đưa tiền cho bạn.", category = "verb"),
    LearningItem("w5_kan", "看", "kàn", "Xem / Nhìn", usageNote = "Xem hàng hóa.", exampleSentence = "我看一看。", examplePinyin = "wǒ kàn yí kàn.", exampleTranslation = "Tôi xem một chút nhé.", category = "verb"),

    // Node 5: How Many? (Quantities & Measure Word)
    LearningItem("w5_ge", "个", "gè", "Cái / Chiếc / Quả (Lượng từ)", usageNote = "Lượng từ vạn năng thông dụng nhất.", exampleSentence = "一个苹果。", examplePinyin = "yí gè píngguǒ.", exampleTranslation = "Một quả táo.", category = "measure"),
    LearningItem("w5_yige", "一个", "yí gè", "Một cái / Một quả", usageNote = "Số lượng 1.", exampleSentence = "我要一个。", examplePinyin = "wǒ yào yí gè.", exampleTranslation = "Tôi lấy một cái.", category = "quantity"),
    LearningItem("w5_liangge", "两个", "liǎng gè", "Hai cái / Hai quả", usageNote = "Lưu ý: Dùng 两个 (liǎng gè) chứ không dùng 二个 khi đếm số lượng.", exampleSentence = "我要两个苹果。", examplePinyin = "wǒ yào liǎng gè píngguǒ.", exampleTranslation = "Tôi lấy hai quả táo.", category = "quantity"),
    LearningItem("w5_sange", "三个", "sān gè", "Ba cái / Ba quả", usageNote = "Số lượng 3.", exampleSentence = "我要三个。", examplePinyin = "wǒ yào sān gè.", exampleTranslation = "Tôi lấy ba cái.", category = "quantity"),
    LearningItem("w5_sige", "四个", "sì gè", "Bốn cái / Bốn quả", usageNote = "Số lượng 4.", exampleSentence = "我要四个苹果。", examplePinyin = "wǒ yào sì gè píngguǒ.", exampleTranslation = "Tôi lấy bốn quả táo.", category = "quantity"),
    LearningItem("w5_wuge", "五个", "wǔ gè", "Năm cái / Năm quả", usageNote = "Số lượng 5.", exampleSentence = "我要五个。", examplePinyin = "wǒ yào wǔ gè.", exampleTranslation = "Tôi lấy năm cái.", category = "quantity"),

    // Node 6: Too Expensive! (Reactions)
    LearningItem("w5_tai", "太", "tài", "Quá / Lắm (Too/Very)", usageNote = "Biểu thị mức độ vượt mức thường (太...了).", exampleSentence = "太贵了！", examplePinyin = "tài guì le!", exampleTranslation = "Đắt quá rồi!", category = "grammar"),
    LearningItem("w5_yidianr", "一点儿", "yìdiǎnr", "Một chút / Một ít", usageNote = "Chỉ số lượng hoặc mức độ nhỏ.", exampleSentence = "便宜一点儿吧。", examplePinyin = "piányi yìdiǎnr ba.", exampleTranslation = "Rẻ một chút nhé.", category = "adverb"),
    LearningItem("w5_hao", "好", "hǎo", "Được / Tốt / Đồng ý", usageNote = "Chấp thuận thỏa thuận.", exampleSentence = "好，我买。", examplePinyin = "hǎo, wǒ mǎi.", exampleTranslation = "Được rồi, tôi mua.", category = "response"),

    // Node 7: A Little Cheaper (Bargaining)
    LearningItem("w5_haoba", "好吧", "hǎoba", "Được thôi / Thôi được rồi", usageNote = "Đồng ý có chút nhượng bộ khi trả giá.", exampleSentence = "好吧，给你十块。", examplePinyin = "hǎoba, gěi nǐ shí kuài.", exampleTranslation = "Thôi được rồi, bớt cho bạn 10 đồng.", category = "response"),
    LearningItem("w5_xiexie", "谢谢", "xièxie", "Cảm ơn", usageNote = "Lời cảm ơn sau khi mua bán thành công.", exampleSentence = "谢谢老板！", examplePinyin = "xièxie lǎobǎn!", exampleTranslation = "Cảm ơn ông chủ!", category = "polite")
  )

  val itemMap: Map<String, LearningItem> by lazy {
    items.associateBy { it.id }
  }

  // -------------------------------------------------------------
  // NODE 1: BEIJING MARKET (北京市场)
  // -------------------------------------------------------------
  private val node1Lessons = listOf(
    MicroLesson(
      id = "w5_n1_l1",
      nodeId = "w5_n1",
      title = "Khám Phá Chợ Bắc Kinh",
      subtitle = "Học từ vựng chợ (市场), mua (买), bán (卖), đồ đạc (东西), ông chủ (老板)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n1_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_shichang", "w5_mai_buy", "w5_mai_sell", "w5_dongxi"), "Khám phá không gian chợ mua sắm", audioText = "市场 买 卖 东西", correctAnswer = ""),
        LearningActivity("w5_n1_a2", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_qian", "w5_shangdian", "w5_laoban"), "Khám phá tiền tệ và chủ tiệm", audioText = "钱 商店 老板", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n1_l2",
      nodeId = "w5_n1",
      title = "Phân Biệt Âm Mua (买) & Bán (卖)",
      subtitle = "Luyện nghe phân biệt thanh điệu: mǎi (mua) vs mài (bán)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n1_a3", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_mai_buy"), "Nghe và chọn nghĩa của từ:", audioText = "买", options = listOf("mǎi (Mua - Thanh 3)", "mài (Bán - Thanh 4)", "qián (Tiền)", "shìchǎng (Chợ)"), correctAnswer = "mǎi (Mua - Thanh 3)", explanation = "买 (mǎi) mang thanh 3 nghĩa là Mua; 卖 (mài) mang thanh 4 nghĩa là Bán."),
        LearningActivity("w5_n1_a4", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w5_shichang"), "Nghe phát âm và chọn chữ Hán cho \"Chợ\":", audioText = "市场", options = listOf("市场", "商店", "东西", "学校"), correctAnswer = "市场")
      )
    ),
    MicroLesson(
      id = "w5_n1_l3",
      nodeId = "w5_n1",
      title = "Ghép Câu Đi Chợ & Tham Quan",
      subtitle = "Tạo câu: 这是市场 & 我去市场买东西",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n1_a5", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_shichang"), "Ghép câu: \"Tôi đi chợ mua đồ.\"", sentenceWords = listOf("我", "去", "市场", "买", "东西"), targetSentence = "我去市场买东西", correctAnswer = "我去市场买东西"),
        LearningActivity("w5_n1_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_shichang"), "Gấu trúc dẫn bạn đến cổng chợ Bắc Kinh:", pandaDialogue = "欢迎来到北京市场！你想买什么？", options = listOf("我想去买东西。", "现在十点。", "他是我同学。"), correctAnswer = "我想去买东西。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 2: I WANT TO BUY... (我想买...)
  // -------------------------------------------------------------
  private val node2Lessons = listOf(
    MicroLesson(
      id = "w5_n2_l1",
      nodeId = "w5_n2",
      title = "Khám Phá Các Món Hàng Cần Mua",
      subtitle = "Học muốn (想/要), nước (水), trà (茶), hoa quả (水果), táo (苹果)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n2_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_xiang", "w5_yao", "w5_shui", "w5_cha", "w5_shuiguo", "w5_pingguo", "w5_shu"), "Khám phá các mặt hàng", audioText = "想 要 水 茶 水果 苹果 书", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n2_l2",
      nodeId = "w5_n2",
      title = "Luyện Nghe Món Hàng",
      subtitle = "Nghe và nhận diện píngguǒ, shuǐ, chá",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n2_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_pingguo"), "Nghe và chọn Pinyin cho \"Quả táo\":", audioText = "苹果", options = listOf("píngguǒ", "shuǐguǒ", "chá", "shuǐ"), correctAnswer = "píngguǒ"),
        LearningActivity("w5_n2_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w5_shui"), "Nghe và chọn chữ Hán cho \"Nước\":", audioText = "水", options = listOf("水", "茶", "苹果", "水果"), correctAnswer = "水")
      )
    ),
    MicroLesson(
      id = "w5_n2_l3",
      nodeId = "w5_n2",
      title = "Ghép Câu Bày Tỏ Nhu Cầu Mua Sắm",
      subtitle = "Tạo câu: 我想买苹果 & 我要买茶",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n2_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_pingguo"), "Ghép câu: \"Tôi muốn mua táo.\"", sentenceWords = listOf("我", "想", "买", "苹果", "水"), targetSentence = "我想买苹果", correctAnswer = "我想买苹果"),
        LearningActivity("w5_n2_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_pingguo"), "Tại quầy hoa quả, chủ tiệm hỏi:", pandaDialogue = "你好！你要买什么？", options = listOf("我想买苹果。", "我是学生。", "今天星期二。"), correctAnswer = "我想买苹果。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 3: HOW MUCH? (多少钱？)
  // -------------------------------------------------------------
  private val node3Lessons = listOf(
    MicroLesson(
      id = "w5_n3_l1",
      nodeId = "w5_n3",
      title = "Khám Phá Hỏi Giá & Tiền Tệ",
      subtitle = "Học bao nhiêu tiền (多少钱), đồng/khối (块), tệ (元), đắt (贵), rẻ (便宜)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n3_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_duoshaoqian", "w5_duoshao", "w5_kuai", "w5_yuan", "w5_gui", "w5_pianyi"), "Khám phá từ vựng giá tiền", audioText = "多少钱 多少 块 元 贵 便宜", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n3_l2",
      nodeId = "w5_n3",
      title = "Luyện Nghe Giá Tiền",
      subtitle = "Nghe số tiền kết hợp đơn vị 块 (kuài)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n3_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_duoshaoqian"), "Nghe mẫu câu hỏi giá kinh điển:", audioText = "这个多少钱", options = listOf("zhège duōshao qián?", "nǐ zài nǎr?", "nǐ jǐ suì?", "shàngkè le"), correctAnswer = "zhège duōshao qián?"),
        LearningActivity("w5_n3_a3", ActivityType.MULTIPLE_CHOICE, SkillType.LISTENING, listOf("w5_kuai"), "Chủ tiệm nói: \"十块钱 (shí kuài qián)\". Giá món hàng là bao nhiêu?", audioText = "十块钱", options = listOf("10 tệ (10 đồng)", "20 tệ", "5 tệ", "100 tệ"), correctAnswer = "10 tệ (10 đồng)")
      )
    ),
    MicroLesson(
      id = "w5_n3_l3",
      nodeId = "w5_n3",
      title = "Ghép Câu & Hỏi Giá Hàng Hóa",
      subtitle = "Tạo câu: 这个多少钱？",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n3_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_duoshaoqian"), "Ghép câu hỏi giá: \"Cái này bao nhiêu tiền?\"", sentenceWords = listOf("这个", "多少", "钱", "？", "买"), targetSentence = "这个多少钱？", correctAnswer = "这个多少钱？"),
        LearningActivity("w5_n3_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_duoshaoqian"), "Bạn cầm quả táo và hỏi chủ tiệm:", pandaDialogue = "老板，这个苹果多少钱？", options = listOf("五块钱一个。", "我叫 {{learner.name}}。", "在图书馆。"), correctAnswer = "五块钱一个。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 4: I WANT THIS ONE (我要这个)
  // -------------------------------------------------------------
  private val node4Lessons = listOf(
    MicroLesson(
      id = "w5_n4_l1",
      nodeId = "w5_n4",
      title = "Khám Phá Chỉ Định Món Hàng",
      subtitle = "Học cái này (这个), cái kia (那个), đưa cho (给), xem (看), không lấy (不要)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n4_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_zhege", "w5_nage", "w5_buyao", "w5_gei", "w5_kan"), "Khám phá từ chỉ định chọn hàng", audioText = "这个 那个 不要 给 看", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n4_l2",
      nodeId = "w5_n4",
      title = "Luyện Nghe Lựa Chọn",
      subtitle = "Phân biệt zhège (cái này) và nàge (cái kia)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n4_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_zhege"), "Nghe và chọn Pinyin cho \"Cái này\":", audioText = "这个", options = listOf("zhège", "nàge", "bú yào", "gěi"), correctAnswer = "zhège"),
        LearningActivity("w5_n4_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w5_gei"), "Nghe và chọn chữ Hán cho \"Đưa cho\":", audioText = "给", options = listOf("给", "看", "要", "买"), correctAnswer = "给")
      )
    ),
    MicroLesson(
      id = "w5_n4_l3",
      nodeId = "w5_n4",
      title = "Ghép Câu & Chốt Lấy Món Hàng",
      subtitle = "Tạo câu: 我要这个 & 给你钱",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n4_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_zhege"), "Ghép câu: \"Tôi lấy cái này.\"", sentenceWords = listOf("我", "要", "这个", "那个", "给"), targetSentence = "我要这个", correctAnswer = "我要这个"),
        LearningActivity("w5_n4_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_zhege"), "Chủ quán hỏi bạn muốn lấy loại nào:", pandaDialogue = "你要这个红苹果，还是那个绿苹果？", options = listOf("我要这个红苹果。", "现在九点。", "我有四个同学。"), correctAnswer = "我要这个红苹果。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 5: HOW MANY? (多少个？)
  // -------------------------------------------------------------
  private val node5Lessons = listOf(
    MicroLesson(
      id = "w5_n5_l1",
      nodeId = "w5_n5",
      title = "Khám Phá Lượng Từ & Số Lượng Mua",
      subtitle = "Học lượng từ 个, một cái (一个), hai cái (两个 - liǎng gè), ba cái (三个)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n5_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_ge", "w5_yige", "w5_liangge", "w5_sange", "w5_sige", "w5_wuge"), "Khám phá lượng từ số lượng", audioText = "个 一个 两个 三个 四个 五个", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n5_l2",
      nodeId = "w5_n5",
      title = "Lưu Ý Vàng: 两个 (Liǎng gè) vs 二 (Èr)",
      subtitle = "Khi đếm số lượng đồ vật, bắt buộc dùng 两个 thay vì 二个",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n5_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_liangge"), "Nghe cụm từ \"Hai cái\":", audioText = "两个", options = listOf("liǎng gè", "èr gè", "sān gè", "yí gè"), correctAnswer = "liǎng gè", explanation = "Trong tiếng Trung, khi đứng trước lượng từ ta dùng 两 (liǎng)."),
        LearningActivity("w5_n5_a3", ActivityType.MULTIPLE_CHOICE, SkillType.GRAMMAR, listOf("w5_liangge"), "Cách nói nào đúng cho \"2 quả táo\"?", options = listOf("两个苹果 (liǎng gè píngguǒ)", "二个苹果", "两个钱", "二钱苹果"), correctAnswer = "两个苹果 (liǎng gè píngguǒ)")
      )
    ),
    MicroLesson(
      id = "w5_n5_l3",
      nodeId = "w5_n5",
      title = "Ghép Câu Đặt Số Lượng",
      subtitle = "Tạo câu: 我要三个苹果",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n5_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_sange"), "Ghép câu: \"Tôi muốn lấy ba quả táo.\"", sentenceWords = listOf("我", "要", "三个", "苹果", "多少"), targetSentence = "我要三个苹果", correctAnswer = "我要三个苹果"),
        LearningActivity("w5_n5_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_duoshao"), "Người bán hàng hỏi số lượng:", pandaDialogue = "你要几个苹果？", options = listOf("我要三个苹果。", "三十块钱。", "他是老师。"), correctAnswer = "我要三个苹果。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 6: TOO EXPENSIVE (太贵了)
  // -------------------------------------------------------------
  private val node6Lessons = listOf(
    MicroLesson(
      id = "w5_n6_l1",
      nodeId = "w5_n6",
      title = "Khám Phá Mẫu Câu Bày Tỏ Cảm Thán Giá Cả",
      subtitle = "Học quá (太...了), đắt (贵), rẻ một chút (便宜一点儿)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n6_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_tai", "w5_gui", "w5_pianyi", "w5_yidianr", "w5_hao"), "Khám phá câu cảm thán giá cả", audioText = "太 贵 便宜 一点儿 好", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n6_l2",
      nodeId = "w5_n6",
      title = "Luyện Nghe Phản Xạ Giá Cao",
      subtitle = "Nghe câu cảm thán: 太贵了！",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n6_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_tai"), "Nghe và chọn nghĩa của câu:", audioText = "太贵了", options = listOf("tài guì le (Đắt quá rồi)", "hěn piányi (Rất rẻ)", "xièxie (Cảm ơn)", "duōshao qián"), correctAnswer = "tài guì le (Đắt quá rồi)"),
        LearningActivity("w5_n6_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w5_gui"), "Nghe và chọn chữ Hán cho \"Đắt\":", audioText = "贵", options = listOf("贵", "买", "卖", "钱"), correctAnswer = "贵")
      )
    ),
    MicroLesson(
      id = "w5_n6_l3",
      nodeId = "w5_n6",
      title = "Ghép Câu Phản Ứng Khi Bị Hét Giá",
      subtitle = "Tạo câu: 太贵了，便宜一点儿吧",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n6_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_gui"), "Ghép câu: \"Đắt quá rồi.\"", sentenceWords = listOf("太", "贵", "了", "便宜", "好"), targetSentence = "太贵了", correctAnswer = "太贵了"),
        LearningActivity("w5_n6_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_gui"), "Chủ quán bảo cái áo 100 tệ, bạn phản ứng:", pandaDialogue = "这件衣服一百块钱！", options = listOf("太贵了！便宜一点儿可以吗？", "好，给我十个。", "我在大学学习。"), correctAnswer = "太贵了！便宜一点儿可以吗？")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 7: A LITTLE CHEAPER (便宜一点儿)
  // -------------------------------------------------------------
  private val node7Lessons = listOf(
    MicroLesson(
      id = "w5_n7_l1",
      nodeId = "w5_n7",
      title = "Nghệ Thuật Trả Giá Thân Thiện",
      subtitle = "Học có thể rẻ một chút không (可以便宜一点儿吗？), thôi được rồi (好吧), cảm ơn (谢谢)",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n7_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w5_pianyi", "w5_yidianr", "w5_haoba", "w5_xiexie"), "Khám phá từ ngữ mặc cả", audioText = "便宜 一点儿 好吧 谢谢", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w5_n7_l2",
      nodeId = "w5_n7",
      title = "Luyện Nghe Cuộc Đàm Phán Giá",
      subtitle = "Nghe câu: 十块钱可以吗？ - 好吧！",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n7_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w5_yidianr"), "Nghe và chọn Pinyin cho \"Một chút\":", audioText = "一点儿", options = listOf("yìdiǎnr", "piányi", "kěyǐ", "hǎoba"), correctAnswer = "yìdiǎnr"),
        LearningActivity("w5_n7_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w5_haoba"), "Nghe lời đồng ý nhượng bộ của người bán:", audioText = "好吧", options = listOf("好吧", "谢谢", "太贵", "不要"), correctAnswer = "好吧")
      )
    ),
    MicroLesson(
      id = "w5_n7_l3",
      nodeId = "w5_n7",
      title = "Ghép Câu Thương Lượng Hoàn Chỉnh",
      subtitle = "Tạo câu: 便宜一点儿可以吗？",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w5_n7_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w5_pianyi"), "Ghép câu trả giá: \"Có thể rẻ một chút không?\"", sentenceWords = listOf("便宜", "一点儿", "可以", "吗", "？"), targetSentence = "便宜一点儿可以吗？", correctAnswer = "便宜一点儿可以吗？"),
        LearningActivity("w5_n7_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_xiexie"), "Chủ quán đồng ý giảm giá, bạn đáp lễ:", pandaDialogue = "好吧，算你八块钱！", options = listOf("太好了，谢谢老板！给你钱。", "我不知道。", "现在三点。"), correctAnswer = "太好了，谢谢老板！给你钱。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 8: MARKET SHOPPING (市场购物) - REAL-WORLD SIMULATION
  // -------------------------------------------------------------
  private val node8Lessons = listOf(
    MicroLesson(
      id = "w5_n8_l1",
      nodeId = "w5_n8",
      title = "Mô Phỏng Trọn Vẹn Giao Tiếp Mua Hoa Quả",
      subtitle = "Chuỗi chào hỏi, hỏi hàng, số lượng, hỏi giá, mặc cả và thanh toán",
      type = "real_world_simulation",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n8_a1", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_pingguo"), "Chủ quầy hoa quả tươi chào đón:", pandaDialogue = "你好！你想买什么水果？", options = listOf("你好！我想买苹果，这个多少钱？", "我是老师。", "我家有五个人。"), correctAnswer = "你好！我想买苹果，这个多少钱？"),
        LearningActivity("w5_n8_a2", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_sange"), "Chủ quầy báo giá và hỏi số lượng:", pandaDialogue = "十块钱一斤，你要几个？", options = listOf("我要三个苹果，太贵了，可以便宜一点儿吗？", "我不喜欢喝茶。", "今天星期天。"), correctAnswer = "我要三个苹果，太贵了，可以便宜一点儿吗？"),
        LearningActivity("w5_n8_a3", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w5_gei"), "Chủ quầy đồng ý: \"好吧，八块钱给你三个！\"", pandaDialogue = "好吧，八块钱给你三个！", options = listOf("好，给你八块钱，谢谢老板！", "再见！", "他在操场。"), correctAnswer = "好，给你八块钱，谢谢老板！")
      )
    ),
    MicroLesson(
      id = "w5_n8_l2",
      nodeId = "w5_n8",
      title = "Luyện Nói Mua Hàng & Viết Chữ Hán",
      subtitle = "Thực hành phát âm lưu loát một câu mua sắm hoàn chỉnh",
      type = "speaking_writing",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n8_a4", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w5_pingguo"), "Nói câu mua sắm tại chợ:", hanziPrompt = "我想买两个苹果，多少钱？", pinyinPrompt = "wǒ xiǎng mǎi liǎng gè píngguǒ, duōshao qián?", audioText = "我想买两个苹果，多少钱", correctAnswer = "我想买两个苹果，多少钱"),
        LearningActivity("w5_n8_a5", ActivityType.WRITING, SkillType.WRITING, listOf("w5_mai_buy"), "Gõ chữ Hán cho \"mǎi\" (Mua):", pinyinPrompt = "mǎi", correctAnswer = "买")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 9: BEIJING MARKET CHALLENGE (北京市场挑战) - WORLD 5 BOSS
  // -------------------------------------------------------------
  private val node9Lessons = listOf(
    MicroLesson(
      id = "w5_n9_l1",
      nodeId = "w5_n9",
      title = "Nhiệm Vụ Mua Sắm Chợ Bắc Kinh - Vòng 1 đến 6",
      subtitle = "Gấu trúc giao danh sách mua sắm: 3 quả táo, 2 chai nước, 1 hộp trà",
      type = "boss_challenge",
      order = 1,
      activities = listOf(
        LearningActivity("w5_n9_a1", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_shichang"), "Vòng 1 (Vào chợ): Gấu trúc giao nhiệm vụ mua sắm:", pandaDialogue = "任务：买三个苹果、两瓶水、一盒茶。准备好了吗？", options = listOf("准备好了，我去买东西！", "我想睡觉。", "这是我学校。"), correctAnswer = "准备好了，我去买东西！", roundNumber = 1),
        LearningActivity("w5_n9_a2", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_pingguo"), "Vòng 2 (Quầy táo): Hỏi người bán táo:", pandaDialogue = "老板：你要买什么？", options = listOf("老板，我想买三个苹果，多少钱？", "我今年二十岁。", "现在七点半。"), correctAnswer = "老板，我想买三个苹果，多少钱？", roundNumber = 2),
        LearningActivity("w5_n9_a3", ActivityType.BOSS_ROUND, SkillType.LISTENING, listOf("w5_gui"), "Vòng 3 (Nghe giá): Người bán nói: \"十五块钱！(shíwǔ kuài qián)\"", audioText = "十五块钱", options = listOf("太贵了，十块钱可以吗？", "好，我要五十个。", "我是医生。"), correctAnswer = "太贵了，十块钱可以吗？", roundNumber = 3),
        LearningActivity("w5_n9_a4", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_haoba"), "Vòng 4 (Chốt giá táo): Người bán nói: \"好吧，十块钱给你！\"", pandaDialogue = "老板：好吧，十块钱给你！", options = listOf("好，给你十块钱，谢谢！", "我不买水。", "明天星期六。"), correctAnswer = "好，给你十块钱，谢谢！", roundNumber = 4),
        LearningActivity("w5_n9_a5", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_shui"), "Vòng 5 (Quầy nước giải khát): Mua 2 chai nước:", pandaDialogue = "饮料摊老板：你好，要喝什么？", options = listOf("我要两瓶水，多少钱？", "我在教室。", "这是桌子。"), correctAnswer = "我要两瓶水，多少钱？", roundNumber = 5),
        LearningActivity("w5_n9_a6", ActivityType.BOSS_ROUND, SkillType.LISTENING, listOf("w5_kuai"), "Vòng 6 (Nghe thanh toán nước): Người bán báo giá nước:", audioText = "一共四块钱", options = listOf("给你四块钱，谢谢！", "太贵了，一百块。", "我不喝水。"), correctAnswer = "给你四块钱，谢谢！", roundNumber = 6)
      )
    ),
    MicroLesson(
      id = "w5_n9_l2",
      nodeId = "w5_n9",
      title = "Nhiệm Vụ Mua Sắm Chợ Bắc Kinh - Vòng 7 đến 11",
      subtitle = "Mua trà, trả giá, thuyết trình hoàn thành nhiệm vụ và ghi chép chữ Hán",
      type = "boss_challenge",
      order = 2,
      activities = listOf(
        LearningActivity("w5_n9_a7", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_cha"), "Vòng 7 (Quầy trà): Mua 1 hộp trà thơm:", pandaDialogue = "茶庄老板：欢迎品茶，你要买茶吗？", options = listOf("我想买一盒中国茶，这个多少钱？", "我是一名学生。", "我爸爸喜欢猫。"), correctAnswer = "我想买一盒中国茶，这个多少钱？", roundNumber = 7),
        LearningActivity("w5_n9_a8", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w5_pianyi"), "Vòng 8 (Thương lượng trà): Báo giá 30 tệ, thương lượng giảm giá:", pandaDialogue = "茶庄老板：这盒好茶三十块钱。", options = listOf("老板，二十块钱可以吗？", "太便宜了，我给一百。", "我不要茶。"), correctAnswer = "老板，二十块钱可以吗？", roundNumber = 8),
        LearningActivity("w5_n9_a9", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w5_shichang"), "Vòng 9 (Nói): Báo cáo nhiệm vụ với Gấu trúc:", hanziPrompt = "我在北京市场买了三个苹果、两瓶水和一盒茶！", pinyinPrompt = "wǒ zài Běijīng shìchǎng mǎi le sān gè píngguǒ, liǎng píng shuǐ hé yì hé chá!", audioText = "我在北京市场买了三个苹果两瓶水和一盒茶", correctAnswer = "我在北京市场买了三个苹果两瓶水和一盒茶", roundNumber = 9),
        LearningActivity("w5_n9_a10", ActivityType.LISTEN_HANZI, SkillType.LISTENING, listOf("w5_duoshaoqian"), "Vòng 10 (Nghe không phụ đề): Nghe câu hỏi kinh điển của khách hàng:", audioText = "这个多少钱", options = listOf("这个多少钱", "我想买苹果", "太贵了", "给你钱"), correctAnswer = "这个多少钱", roundNumber = 10),
        LearningActivity("w5_n9_a11", ActivityType.WRITING, SkillType.WRITING, listOf("w5_qian"), "Vòng 11 (Viết chữ Hán): Gõ chữ Hán cho \"qián\" (Tiền):", pinyinPrompt = "qián", correctAnswer = "钱", roundNumber = 11)
      )
    )
  )

  // -------------------------------------------------------------
  // ALL WORLD 5 NODE COURSES
  // -------------------------------------------------------------
  val world5NodeCourses = listOf(
    NodeCourseData(
      nodeId = "w5_n1",
      title = "Beijing Market",
      subtitle = "北京市场 • Không gian chợ mua sắm",
      description = "Khám phá chợ (市场), mua (买), bán (卖), hàng hóa (东西), tiền tệ (钱) và chủ tiệm (老板).",
      order = 1,
      vocabulary = listOf(
        itemMap["w5_shichang"]!!,
        itemMap["w5_mai_buy"]!!,
        itemMap["w5_mai_sell"]!!,
        itemMap["w5_dongxi"]!!,
        itemMap["w5_qian"]!!,
        itemMap["w5_shangdian"]!!,
        itemMap["w5_laoban"]!!
      ),
      microLessons = node1Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n2",
      title = "I Want to Buy...",
      subtitle = "我想买... • Nhu cầu mua sắm",
      description = "Diễn đạt ý muốn mua: nước (水), trà (茶), hoa quả (水果), táo (苹果), sách (书).",
      order = 2,
      vocabulary = listOf(
        itemMap["w5_xiang"]!!,
        itemMap["w5_yao"]!!,
        itemMap["w5_shui"]!!,
        itemMap["w5_cha"]!!,
        itemMap["w5_shuiguo"]!!,
        itemMap["w5_pingguo"]!!,
        itemMap["w5_shu"]!!
      ),
      microLessons = node2Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n3",
      title = "How Much?",
      subtitle = "多少钱？ • Hỏi & Hiểu giá tiền",
      description = "Hỏi giá và nhận biết mệnh giá: 这个多少钱？, 十块钱 (shí kuài qián), 贵 (guì), 便宜 (piányi).",
      order = 3,
      vocabulary = listOf(
        itemMap["w5_duoshaoqian"]!!,
        itemMap["w5_duoshao"]!!,
        itemMap["w5_kuai"]!!,
        itemMap["w5_yuan"]!!,
        itemMap["w5_gui"]!!,
        itemMap["w5_pianyi"]!!
      ),
      microLessons = node3Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n4",
      title = "I Want This One",
      subtitle = "我要这个 • Lựa chọn món hàng",
      description = "Chỉ định món đồ ưng ý: cái này (这个), cái kia (那个), đưa cho (给), xem thử (看).",
      order = 4,
      vocabulary = listOf(
        itemMap["w5_zhege"]!!,
        itemMap["w5_nage"]!!,
        itemMap["w5_buyao"]!!,
        itemMap["w5_gei"]!!,
        itemMap["w5_kan"]!!
      ),
      microLessons = node4Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n5",
      title = "How Many?",
      subtitle = "多少个？ • Số lượng & Lượng từ",
      description = "Sử dụng lượng từ 个: 一个, 两个 (liǎng gè), 三个, bốn quả táo, năm chai nước.",
      order = 5,
      vocabulary = listOf(
        itemMap["w5_ge"]!!,
        itemMap["w5_yige"]!!,
        itemMap["w5_liangge"]!!,
        itemMap["w5_sange"]!!,
        itemMap["w5_sige"]!!,
        itemMap["w5_wuge"]!!
      ),
      microLessons = node5Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n6",
      title = "Too Expensive",
      subtitle = "太贵了 • Cảm thán giá cả",
      description = "Phản ứng tự nhiên với mức giá cao: 太贵了！, 太贵了，我不要, 便宜一点儿.",
      order = 6,
      vocabulary = listOf(
        itemMap["w5_tai"]!!,
        itemMap["w5_gui"]!!,
        itemMap["w5_pianyi"]!!,
        itemMap["w5_yidianr"]!!,
        itemMap["w5_hao"]!!
      ),
      microLessons = node6Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n7",
      title = "A Little Cheaper",
      subtitle = "便宜一点儿 • Thương lượng thân thiện",
      description = "Nghệ thuật trả giá: 便宜一点儿, 可以便宜一点儿吗？, 十块钱可以吗？, 好吧, 谢谢.",
      order = 7,
      vocabulary = listOf(
        itemMap["w5_pianyi"]!!,
        itemMap["w5_yidianr"]!!,
        itemMap["w5_haoba"]!!,
        itemMap["w5_xiexie"]!!
      ),
      microLessons = node7Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n8",
      title = "Market Shopping",
      subtitle = "市场购物 • Mô phỏng mua sắm thực tế",
      description = "Mô phỏng chân thực chuỗi giao tiếp từ vào quầy, chọn hàng, thương lượng đến thanh toán.",
      order = 8,
      vocabulary = listOf(
        itemMap["w5_shichang"]!!,
        itemMap["w5_pingguo"]!!,
        itemMap["w5_duoshaoqian"]!!,
        itemMap["w5_sange"]!!,
        itemMap["w5_pianyi"]!!,
        itemMap["w5_gei"]!!
      ),
      microLessons = node8Lessons
    ),
    NodeCourseData(
      nodeId = "w5_n9",
      title = "Beijing Market Challenge",
      subtitle = "北京市场挑战 • Đấu trường Thần Chợ Bắc Kinh",
      description = "Trùm World 5: Hoàn thành nhiệm vụ mua trọn vẹn danh sách hàng hóa thực tế 11 vòng.",
      order = 9,
      vocabulary = listOf(
        itemMap["w5_shichang"]!!,
        itemMap["w5_pingguo"]!!,
        itemMap["w5_shui"]!!,
        itemMap["w5_cha"]!!,
        itemMap["w5_duoshaoqian"]!!,
        itemMap["w5_qian"]!!
      ),
      microLessons = node9Lessons
    )
  )

  // -------------------------------------------------------------
  // WORLD 5 QUESTS
  // -------------------------------------------------------------
  val world5Quests = listOf(
    Quest("w5_q1", "world_5", "Bước Vào Chợ Bắc Kinh", "Khám phá không gian chợ và chào hỏi chủ tiệm", listOf(SkillType.VOCABULARY, SkillType.CONVERSATION), listOf("w5_n1"), xpReward = 25),
    Quest("w5_q2", "world_5", "Tìm Quầy Trái Cây", "Định vị quầy hoa quả tươi ngon", listOf(SkillType.VOCABULARY, SkillType.READING), listOf("w5_n2"), xpReward = 25),
    Quest("w5_q3", "world_5", "Mua Nước Uống", "Mua nước khoáng và trà xanh", listOf(SkillType.LISTENING, SkillType.SPEAKING), listOf("w5_n2"), xpReward = 30),
    Quest("w5_q4", "world_5", "Hỏi Giá Tiền", "Hỏi giá chính xác bằng mẫu câu 这个多少钱", listOf(SkillType.LISTENING, SkillType.SPEAKING), listOf("w5_n3"), xpReward = 30),
    Quest("w5_q5", "world_5", "Chọn Đúng Món Hàng", "Chỉ định rõ ràng món hàng bạn muốn lấy", listOf(SkillType.GRAMMAR, SkillType.READING), listOf("w5_n4"), xpReward = 30),
    Quest("w5_q6", "world_5", "Đếm Số Lượng", "Sử dụng lượng từ 个 và quy tắc 两个 chuẩn xác", listOf(SkillType.GRAMMAR, SkillType.VOCABULARY), listOf("w5_n5"), xpReward = 35),
    Quest("w5_q7", "world_5", "Bậc Thầy Trả Giá", "Thương lượng giá cả khéo léo với chủ tiệm", listOf(SkillType.CONVERSATION, SkillType.SPEAKING), listOf("w5_n6", "w5_n7"), xpReward = 40),
    Quest("w5_q8", "world_5", "Hoàn Thành Danh Sách Mua Sắm", "Vượt qua thử thách Trùm chợ 11 vòng xuất sắc", listOf(SkillType.CONVERSATION, SkillType.SPEAKING, SkillType.WRITING), listOf("w5_n9"), xpReward = 50)
  )

  fun getNodeCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    val course = world5NodeCourses.find { it.nodeId == nodeId } ?: return null
    return if (learnerName.isNotBlank()) course.interpolateLearner(learnerName) else course
  }
}
