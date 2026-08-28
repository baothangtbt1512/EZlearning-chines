package com.example.data

import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.model.PandaEmotion
import com.example.model.SkillType
import com.example.model.interpolateLearner

object World2Curriculum {

  // -------------------------------------------------------------
  // ALL WORLD 2 LEARNING ITEMS
  // -------------------------------------------------------------
  val items = listOf(
    // Node 1: Family
    LearningItem("w2_jia", "家", "jiā", "Nhà / Gia đình", usageNote = "Chữ Nhà/Gia đình. Biểu tượng tổ ấm.", exampleSentence = "我家在北京。", examplePinyin = "wǒ jiā zài Běijīng.", exampleTranslation = "Nhà tôi ở Bắc Kinh.", category = "family"),
    LearningItem("w2_jiaren", "家人", "jiārén", "Người nhà / Thành viên gia đình", usageNote = "家 (nhà) + 人 (người).", exampleSentence = "我的家人都很好。", examplePinyin = "wǒ de jiārén dōu hěn hǎo.", exampleTranslation = "Người nhà tôi đều rất khỏe.", category = "family"),
    LearningItem("w2_baba", "爸爸", "bàba", "Bố / Cha", usageNote = "Danh từ xưng hô thân thương với người cha.", exampleSentence = "这是我的爸爸。", examplePinyin = "zhè shì wǒ de bàba.", exampleTranslation = "Đây là bố của tôi.", category = "family"),
    LearningItem("w2_mama", "妈妈", "māma", "Mẹ / Má", usageNote = "Danh từ xưng hô với người mẹ hiền.", exampleSentence = "这是我的妈妈。", examplePinyin = "zhè shì wǒ de māma.", exampleTranslation = "Đây là mẹ của tôi.", category = "family"),
    LearningItem("w2_gege", "哥哥", "gēge", "Anh trai", usageNote = "Anh trai lớn tuổi hơn.", exampleSentence = "他是我哥哥。", examplePinyin = "tā shì wǒ gēge.", exampleTranslation = "Anh ấy là anh trai tôi.", category = "family"),
    LearningItem("w2_jiejie", "姐姐", "jiějie", "Chị gái", usageNote = "Chị gái trong gia đình.", exampleSentence = "她是我姐姐。", examplePinyin = "tā shì wǒ jiějie.", exampleTranslation = "Cô ấy là chị gái tôi.", category = "family"),
    LearningItem("w2_didi", "弟弟", "dìdi", "Em trai", usageNote = "Em trai nhỏ tuổi hơn.", exampleSentence = "这是我弟弟。", examplePinyin = "zhè shì wǒ dìdi.", exampleTranslation = "Đây là em trai tôi.", category = "family"),
    LearningItem("w2_meimei", "妹妹", "mèimei", "Em gái", usageNote = "Em gái đáng yêu trong gia đình.", exampleSentence = "她是我妹妹。", examplePinyin = "tā shì wǒ mèimei.", exampleTranslation = "Cô ấy là em gái tôi.", category = "family"),

    // Node 2: Friends & Social
    LearningItem("w2_pengyou", "朋友", "péngyou", "Bạn bè / Bạn thân", usageNote = "Bằng hữu - người bạn đồng hành.", exampleSentence = "他是我的朋友。", examplePinyin = "tā shì wǒ de péngyou.", exampleTranslation = "Anh ấy là bạn của tôi.", category = "social"),
    LearningItem("w2_tongxue", "同学", "tóngxué", "Bạn học / Bạn cùng lớp", usageNote = "Đồng học - bạn cùng học một trường/lớp.", exampleSentence = "她是我的同学。", examplePinyin = "tā shì wǒ de tóngxué.", exampleTranslation = "Cô ấy là bạn học của tôi.", category = "social"),
    LearningItem("w2_xuesheng", "学生", "xuésheng", "Học sinh / Sinh viên", usageNote = "Học sinh đang theo học.", exampleSentence = "我也是学生。", examplePinyin = "wǒ yě shì xuésheng.", exampleTranslation = "Tôi cũng là học sinh.", category = "occupation"),
    LearningItem("w2_renshi", "认识", "rènshi", "Quen biết / Nhận biết", usageNote = "Quen biết một ai đó.", exampleSentence = "很高兴认识你！", examplePinyin = "hěn gāoxìng rènshi nǐ!", exampleTranslation = "Rất vui được quen biết bạn!", category = "social"),
    LearningItem("w2_ye", "也", "yě", "Cũng", usageNote = "Phó từ biểu thị sự đồng nhất.", exampleSentence = "我也喜欢中文。", examplePinyin = "wǒ yě xǐhuan Zhōngwén.", exampleTranslation = "Tôi cũng thích tiếng Trung.", category = "grammar"),
    LearningItem("w2_hen", "很", "hěn", "Rất", usageNote = "Phó từ chỉ mức độ cao.", exampleSentence = "我很好。", examplePinyin = "wǒ hěn hǎo.", exampleTranslation = "Tôi rất khỏe / rất tốt.", category = "grammar"),

    // Node 3: Age & Numbers
    LearningItem("w2_sui", "岁", "suì", "Tuổi", usageNote = "Lượng từ chỉ tuổi tác.", exampleSentence = "我十八岁。", examplePinyin = "wǒ shíbā suì.", exampleTranslation = "Tôi 18 tuổi.", category = "age"),
    LearningItem("w2_ji", "几", "jǐ", "Mấy / Bao nhiêu", usageNote = "Từ để hỏi số lượng nhỏ (<10).", exampleSentence = "你几岁？", examplePinyin = "nǐ jǐ suì?", exampleTranslation = "Bạn mấy tuổi?", category = "question"),
    LearningItem("w2_duoda", "多大", "duō dà", "Bao nhiêu tuổi / Lớn chừng nào", usageNote = "Hỏi tuổi cho người trưởng thành.", exampleSentence = "你今年多大？", examplePinyin = "nǐ jīnnián duō dà?", exampleTranslation = "Năm nay bạn bao nhiêu tuổi?", category = "age"),
    LearningItem("w2_jinnian", "今年", "jīnnián", "Năm nay", usageNote = "Kim niên - năm hiện tại.", exampleSentence = "我今年二十岁。", examplePinyin = "wǒ jīnnián èrshí suì.", exampleTranslation = "Năm nay tôi 20 tuổi.", category = "time"),
    LearningItem("w2_shiba", "十八", "shíbā", "Mười tám (18)", usageNote = "Mười + tám = 18.", exampleSentence = "他十八岁。", examplePinyin = "tā shíbā suì.", exampleTranslation = "Cậu ấy 18 tuổi.", category = "number"),
    LearningItem("w2_ershi", "二十", "èrshí", "Hai mươi (20)", usageNote = "Hai + mười = 20.", exampleSentence = "我二十岁。", examplePinyin = "wǒ èrshí suì.", exampleTranslation = "Tôi 20 tuổi.", category = "number"),

    // Node 4: Occupations & Actions
    LearningItem("w2_zuo", "做", "zuò", "Làm", usageNote = "Động từ thực hiện công việc, hành động.", exampleSentence = "你做什么？", examplePinyin = "nǐ zuò shénme?", exampleTranslation = "Bạn làm nghề gì / Bạn đang làm gì?", category = "verb"),
    LearningItem("w2_gongzuo", "工作", "gōngzuò", "Công việc / Làm việc", usageNote = "Công tác - nghề nghiệp hoặc đi làm.", exampleSentence = "你在哪里工作？", examplePinyin = "nǐ zài nǎlǐ gōngzuò?", exampleTranslation = "Bạn làm việc ở đâu?", category = "occupation"),
    LearningItem("w2_laoshi", "老师", "lǎoshī", "Thầy cô giáo", usageNote = "Lão sư - người dạy học tôn kính.", exampleSentence = "他是老师。", examplePinyin = "tā shì lǎoshī.", exampleTranslation = "Thầy ấy là giáo viên.", category = "occupation"),
    LearningItem("w2_yisheng", "医生", "yīshēng", "Bác sĩ", usageNote = "Y sinh - bác sĩ chữa bệnh.", exampleSentence = "她是医生。", examplePinyin = "tā shì yīshēng.", exampleTranslation = "Bác ấy là bác sĩ.", category = "occupation"),
    LearningItem("w2_daxuesheng", "大学生", "dàxuéshēng", "Sinh viên đại học", usageNote = "Học sinh bậc đại học.", exampleSentence = "我是大学生。", examplePinyin = "wǒ shì dàxuéshēng.", exampleTranslation = "Tôi là sinh viên đại học.", category = "occupation"),

    // Node 5: Likes & Dislikes
    LearningItem("w2_xihuan", "喜欢", "xǐhuan", "Thích", usageNote = "Bày tỏ sự yêu thích ai/cái gì.", exampleSentence = "我喜欢中文。", examplePinyin = "wǒ xǐhuan Zhōngwén.", exampleTranslation = "Tôi thích tiếng Trung.", category = "preference"),
    LearningItem("w2_buxihuan", "不喜欢", "bù xǐhuan", "Không thích", usageNote = "Không thích điều gì.", exampleSentence = "我不喜欢咖啡。", examplePinyin = "wǒ bù xǐhuan kāfēi.", exampleTranslation = "Tôi không thích cà phê.", category = "preference"),
    LearningItem("w2_yinyue", "音乐", "yīnyuè", "Âm nhạc", usageNote = "Âm nhạc giải trí.", exampleSentence = "我喜欢音乐。", examplePinyin = "wǒ xǐhuan yīnyuè.", exampleTranslation = "Tôi thích âm nhạc.", category = "hobby"),
    LearningItem("w2_cha", "茶", "chá", "Trà", usageNote = "Trà truyền thống Trung Hoa.", exampleSentence = "我喜欢喝茶。", examplePinyin = "wǒ xǐhuan hē chá.", exampleTranslation = "Tôi thích uống trà.", category = "drink"),
    LearningItem("w2_kafei", "咖啡", "kāfēi", "Cà phê", usageNote = "Thức uống cà phê.", exampleSentence = "你喝咖啡吗？", examplePinyin = "nǐ hē kāfēi ma?", exampleTranslation = "Bạn uống cà phê không?", category = "drink"),
    LearningItem("w2_kan", "看", "kàn", "Xem / Đọc / Nhìn", usageNote = "Xem phim, đọc sách, nhìn ngắm.", exampleSentence = "我看书。", examplePinyin = "wǒ kàn shū.", exampleTranslation = "Tôi đọc sách.", category = "verb"),
    LearningItem("w2_ting", "听", "tīng", "Nghe", usageNote = "Thính - lắng nghe âm thanh, âm nhạc.", exampleSentence = "我听音乐。", examplePinyin = "wǒ tīng yīnyuè.", exampleTranslation = "Tôi nghe nhạc.", category = "verb"),
    LearningItem("w2_chi", "吃", "chī", "Ăn", usageNote = "Động từ dùng bữa, ăn thực phẩm.", exampleSentence = "我吃饭。", examplePinyin = "wǒ chī fàn.", exampleTranslation = "Tôi ăn cơm.", category = "verb"),
    LearningItem("w2_he", "喝", "hē", "Uống", usageNote = "Uống nước, uống trà.", exampleSentence = "我喝水。", examplePinyin = "wǒ hē shuǐ.", exampleTranslation = "Tôi uống nước.", category = "verb"),

    // Node 6: Days of Week
    LearningItem("w2_jintian", "今天", "jīntiān", "Hôm nay", usageNote = "Kim thiên - ngày hôm nay.", exampleSentence = "今天星期五。", examplePinyin = "jīntiān xīngqī wǔ.", exampleTranslation = "Hôm nay là thứ Sáu.", category = "time"),
    LearningItem("w2_mingtian", "明天", "míngtiān", "Ngày mai", usageNote = "Minh thiên - ngày mai tươi sáng.", exampleSentence = "明天星期六。", examplePinyin = "míngtiān xīngqī liù.", exampleTranslation = "Ngày mai là thứ Bảy.", category = "time"),
    LearningItem("w2_zuotian", "昨天", "zuótiān", "Hôm qua", usageNote = "Tạc thiên - ngày hôm qua.", exampleSentence = "昨天星期四。", examplePinyin = "zuótiān xīngqī sì.", exampleTranslation = "Hôm qua là thứ Năm.", category = "time"),
    LearningItem("w2_xingqiyi", "星期一", "xīngqī yī", "Thứ Hai (Monday)", usageNote = "Tuần lễ ngày 1.", exampleSentence = "今天星期一。", examplePinyin = "jīntiān xīngqī yī.", exampleTranslation = "Hôm nay là thứ Hai.", category = "calendar"),
    LearningItem("w2_xingqier", "星期二", "xīngqī èr", "Thứ Ba (Tuesday)", usageNote = "Tuần lễ ngày 2.", exampleSentence = "明天星期二。", examplePinyin = "míngtiān xīngqī èr.", exampleTranslation = "Ngày mai là thứ Ba.", category = "calendar"),
    LearningItem("w2_xingqisan", "星期三", "xīngqī sān", "Thứ Tư (Wednesday)", usageNote = "Tuần lễ ngày 3.", exampleSentence = "昨天星期三。", examplePinyin = "zuótiān xīngqī sān.", exampleTranslation = "Hôm qua là thứ Tư.", category = "calendar"),
    LearningItem("w2_xingqisi", "星期四", "xīngqī sì", "Thứ Năm (Thursday)", usageNote = "Tuần lễ ngày 4.", exampleSentence = "今天星期四。", examplePinyin = "jīntiān xīngqī sì.", exampleTranslation = "Hôm nay là thứ Năm.", category = "calendar"),
    LearningItem("w2_xingqiwu", "星期五", "xīngqī wǔ", "Thứ Sáu (Friday)", usageNote = "Tuần lễ ngày 5.", exampleSentence = "今天星期五。", examplePinyin = "jīntiān xīngqī wǔ.", exampleTranslation = "Hôm nay là thứ Sáu.", category = "calendar"),
    LearningItem("w2_xingqiliu", "星期六", "xīngqī liù", "Thứ Bảy (Saturday)", usageNote = "Tuần lễ ngày 6.", exampleSentence = "明天星期六。", examplePinyin = "míngtiān xīngqī liù.", exampleTranslation = "Ngày mai là thứ Bảy.", category = "calendar"),
    LearningItem("w2_xingqiri", "星期日", "xīngqī rì", "Chủ Nhật (Sunday)", usageNote = "Tuần lễ ngày Nhật (Mặt Trời).", exampleSentence = "今天星期日。", examplePinyin = "jīntiān xīngqī rì.", exampleTranslation = "Hôm nay là Chủ Nhật.", category = "calendar"),

    // Node 7: Time
    LearningItem("w2_xianzai", "现在", "xiànzài", "Bây giờ / Hiện tại", usageNote = "Thời điểm ngay lúc này.", exampleSentence = "现在几点？", examplePinyin = "xiànzài jǐ diǎn?", exampleTranslation = "Bây giờ là mấy giờ?", category = "time"),
    LearningItem("w2_jidian", "几点", "jǐ diǎn", "Mấy giờ", usageNote = "Dùng để hỏi thời gian.", exampleSentence = "现在三点。", examplePinyin = "xiànzài sān diǎn.", exampleTranslation = "Bây giờ là ba giờ.", category = "question"),
    LearningItem("w2_dian", "点", "diǎn", "Giờ (o'clock)", usageNote = "Đơn vị chỉ giờ trên đồng hồ.", exampleSentence = "现在八点。", examplePinyin = "xiànzài bā diǎn.", exampleTranslation = "Bây giờ là tám giờ.", category = "time"),
    LearningItem("w2_fen", "分", "fēn", "Phút (minute)", usageNote = "Đơn vị phút chỉ thời gian.", exampleSentence = "三点十分。", examplePinyin = "sān diǎn shí fēn.", exampleTranslation = "Ba giờ mười phút.", category = "time"),
    LearningItem("w2_ban", "半", "bàn", "Rưỡi / Nửa (half)", usageNote = "Chỉ 30 phút (giờ rưỡi).", exampleSentence = "现在三点半。", examplePinyin = "xiànzài sān diǎn bàn.", exampleTranslation = "Bây giờ là ba giờ rưỡi.", category = "time"),

    // Node 8: Daily Routine
    LearningItem("w2_zaoshang", "早上", "zǎoshang", "Buổi sáng sớm", usageNote = "Khoảng thời gian từ 6h đến 9h sáng.", exampleSentence = "我早上起床。", examplePinyin = "wǒ zǎoshang qǐchuáng.", exampleTranslation = "Tôi thức dậy vào buổi sáng.", category = "time"),
    LearningItem("w2_qichuang", "起床", "qǐchuáng", "Thức dậy / Rời giường", usageNote = "Bắt đầu ngày mới rời khỏi giường.", exampleSentence = "我七点起床。", examplePinyin = "wǒ qī diǎn qǐchuáng.", exampleTranslation = "Tôi thức dậy lúc 7 giờ.", category = "routine"),
    LearningItem("w2_chifan", "吃饭", "chīfàn", "Ăn cơm / Dùng bữa", usageNote = "Hành động ăn bữa ăn hàng ngày.", exampleSentence = "我早上吃饭。", examplePinyin = "wǒ zǎoshang chīfàn.", exampleTranslation = "Tôi ăn sáng vào buổi sáng.", category = "routine"),
    LearningItem("w2_xuexi", "学习", "xuéxí", "Học tập / Nghiên cứu", usageNote = "Học kiến thức, ngôn ngữ.", exampleSentence = "我在学习中文。", examplePinyin = "wǒ zài xuéxí Zhōngwén.", exampleTranslation = "Tôi đang học tiếng Trung.", category = "routine"),
    LearningItem("w2_huijia", "回家", "huí jiā", "Về nhà", usageNote = "Trở về với gia đình sau giờ học/làm.", exampleSentence = "我下午回家。", examplePinyin = "wǒ xiàwǔ huí jiā.", exampleTranslation = "Tôi về nhà vào buổi chiều.", category = "routine"),
    LearningItem("w2_shuijiao", "睡觉", "shuìjiào", "Đi ngủ / Nghỉ ngơi", usageNote = "Ngủ ngon giấc vào ban đêm.", exampleSentence = "我晚上睡觉。", examplePinyin = "wǒ wǎnshang shuìjiào.", exampleTranslation = "Tôi đi ngủ vào buổi tối.", category = "routine"),
    LearningItem("w2_wanshang", "晚上", "wǎnshang", "Buổi tối", usageNote = "Khoảng thời gian ban đêm.", exampleSentence = "晚上好！", examplePinyin = "wǎnshang hǎo!", exampleTranslation = "Chào buổi tối!", category = "time"),
    LearningItem("w2_zai", "在", "zài", "Đang / Ở", usageNote = "Phó từ biểu thị hành động đang diễn ra (在 + Động từ).", exampleSentence = "我在学习。", examplePinyin = "wǒ zài xuéxí.", exampleTranslation = "Tôi đang học bài.", category = "grammar")
  )

  val itemMap: Map<String, LearningItem> by lazy {
    items.associateBy { it.id }
  }

  // -------------------------------------------------------------
  // NODE 1: MY FAMILY (我的家人)
  // -------------------------------------------------------------
  private val node1Lessons = listOf(
    MicroLesson(
      id = "w2_n1_l1",
      nodeId = "w2_n1",
      title = "Khám Phá Gia Đình",
      subtitle = "Gặp gỡ các thành viên thân yêu trong gia đình",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n1_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_jia", "w2_jiaren", "w2_baba", "w2_mama"), "Khám phá danh xưng gia đình", audioText = "家人 爸爸 妈妈", correctAnswer = ""),
        LearningActivity("w2_n1_a2", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_gege", "w2_jiejie", "w2_didi", "w2_meimei"), "Khám phá anh chị em", audioText = "哥哥 姐姐 弟弟 妹妹", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n1_l2",
      nodeId = "w2_n1",
      title = "Nghe & Nhận Diện Pinyin",
      subtitle = "Luyện thính giác phân biệt cách gọi cha mẹ",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n1_a3", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_baba"), "Nghe âm thanh và chọn Pinyin đúng:", audioText = "爸爸", options = listOf("bàba", "māma", "gēge", "dìdi"), correctAnswer = "bàba", explanation = "bàba nghĩa là Bố/Cha."),
        LearningActivity("w2_n1_a4", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_mama"), "Nghe âm thanh và chọn Pinyin đúng:", audioText = "妈妈", options = listOf("māma", "jiějie", "mèimei", "jiārén"), correctAnswer = "māma", explanation = "māma nghĩa là Mẹ.")
      )
    ),
    MicroLesson(
      id = "w2_n1_l3",
      nodeId = "w2_n1",
      title = "Nhận Diện Chữ Hán",
      subtitle = "Nối âm thanh với mặt chữ Hán",
      type = "listen_hanzi",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n1_a5", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_gege"), "Nghe phát âm và chọn chữ Hán tương ứng:", audioText = "哥哥", options = listOf("哥哥", "姐姐", "弟弟", "妹妹"), correctAnswer = "哥哥", explanation = "哥哥 (gēge) là Anh trai."),
        LearningActivity("w2_n1_a6", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_jiejie"), "Nghe phát âm và chọn chữ Hán tương ứng:", audioText = "姐姐", options = listOf("姐姐", "妈妈", "爸爸", "家人"), correctAnswer = "姐姐", explanation = "姐姐 (jiějie) là Chị gái.")
      )
    ),
    MicroLesson(
      id = "w2_n1_l4",
      nodeId = "w2_n1",
      title = "Đại Từ 他 và 她",
      subtitle = "Phân biệt Anh ấy (他) và Cô ấy (她)",
      type = "multiple_choice",
      order = 4,
      activities = listOf(
        LearningActivity("w2_n1_a7", ActivityType.MULTIPLE_CHOICE, SkillType.GRAMMAR, listOf("w2_baba"), "Chọn câu đúng để giới thiệu về Bố:", hanziPrompt = "___是我爸爸。", options = listOf("他", "她", "我", "你"), correctAnswer = "他", explanation = "\"他\" (tā) dùng cho nam giới (Bố, anh trai, em trai)."),
        LearningActivity("w2_n1_a8", ActivityType.MULTIPLE_CHOICE, SkillType.GRAMMAR, listOf("w2_mama"), "Chọn câu đúng để giới thiệu về Mẹ:", hanziPrompt = "___是我妈妈。", options = listOf("她", "他", "你", "很"), correctAnswer = "她", explanation = "\"她\" (tā) có bộ Nữ (女) dùng cho nữ giới (Mẹ, chị gái, em gái).")
      )
    ),
    MicroLesson(
      id = "w2_n1_l5",
      nodeId = "w2_n1",
      title = "Ghép Câu Gia Đình",
      subtitle = "Tạo câu hoàn chỉnh: 这是我的爸爸",
      type = "sentence_builder",
      order = 5,
      activities = listOf(
        LearningActivity("w2_n1_a9", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_baba"), "Ghép câu: \"Đây là bố của tôi.\"", sentenceWords = listOf("这是", "我的", "爸爸", "妈妈", "他"), targetSentence = "这是我的爸爸", correctAnswer = "这是我的爸爸", explanation = "这是 (đây là) + 我的 (của tôi) + 爸爸 (bố)."),
        LearningActivity("w2_n1_a10", ActivityType.FILL_BLANK, SkillType.VOCABULARY, listOf("w2_mama"), "Điền từ còn thiếu vào chỗ trống:", hanziPrompt = "这是我的___。", options = listOf("妈妈", "水", "叫", "谢谢"), correctAnswer = "妈妈", explanation = "Hoàn thiện: 这是我的妈妈 (Đây là mẹ của tôi).")
      )
    ),
    MicroLesson(
      id = "w2_n1_l6",
      nodeId = "w2_n1",
      title = "Hội Thoại Cùng Gấu Trúc",
      subtitle = "Trò chuyện và giới thiệu gia đình cho bạn đồng hành",
      type = "panda_conversation",
      order = 6,
      activities = listOf(
        LearningActivity("w2_n1_a11", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_baba"), "Gấu trúc hỏi bạn:", pandaDialogue = "这是你的爸爸吗？", options = listOf("是的，这是我的爸爸。", "我不客气。", "你好吗？"), correctAnswer = "是的，这是我的爸爸。", explanation = "Đáp lại xác nhận người thân một cách tự nhiên.")
      )
    ),
    MicroLesson(
      id = "w2_n1_l7",
      nodeId = "w2_n1",
      title = "Luyện Nói & Viết",
      subtitle = "Phát âm câu gia đình và gõ chữ Hán chuẩn xác",
      type = "speaking_writing",
      order = 7,
      activities = listOf(
        LearningActivity("w2_n1_a12", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w2_baba"), "Nhấn mic và đọc to câu sau:", hanziPrompt = "这是我的爸爸。", pinyinPrompt = "zhè shì wǒ de bàba.", audioText = "这是我的爸爸", correctAnswer = "这是我的爸爸"),
        LearningActivity("w2_n1_a13", ActivityType.WRITING, SkillType.WRITING, listOf("w2_jiaren"), "Gõ chữ Hán tương ứng với Pinyin: \"jiārén\"", pinyinPrompt = "jiārén", correctAnswer = "家人", explanation = "家人 = Người nhà / Gia đình.")
      )
    ),
    MicroLesson(
      id = "w2_n1_l8",
      nodeId = "w2_n1",
      title = "Ôn Tập Tổng Hợp",
      subtitle = "Củng cố kiến thức gia đình kết hợp chào hỏi World 1",
      type = "mixed_review",
      order = 8,
      activities = listOf(
        LearningActivity("w2_n1_a14", ActivityType.MIXED_REVIEW, SkillType.VOCABULARY, listOf("w2_didi"), "Em trai trong tiếng Trung là gì?", options = listOf("弟弟", "哥哥", "姐姐", "妹妹"), correctAnswer = "弟弟", isReviewItem = true)
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 2: MY FRIENDS (我的朋友)
  // -------------------------------------------------------------
  private val node2Lessons = listOf(
    MicroLesson(
      id = "w2_n2_l1",
      nodeId = "w2_n2",
      title = "Khám Phá Bạn Bè",
      subtitle = "Học từ vựng về bạn bè, bạn học và giao tiếp xã hội",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n2_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_pengyou", "w2_tongxue", "w2_xuesheng", "w2_renshi", "w2_ye", "w2_hen"), "Khám phá từ vựng bạn bè & quan hệ", audioText = "朋友 同学 学生 认识 也 很", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n2_l2",
      nodeId = "w2_n2",
      title = "Luyện Nghe Nhận Diện",
      subtitle = "Nghe và nhận biết bạn bè (朋友) và bạn học (同学)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n2_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_pengyou"), "Nghe âm thanh và chọn Pinyin đúng:", audioText = "朋友", options = listOf("péngyou", "tóngxué", "xuésheng", "rènshi"), correctAnswer = "péngyou", explanation = "péngyou = Bạn bè."),
        LearningActivity("w2_n2_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_tongxue"), "Nghe âm thanh và chọn chữ Hán đúng:", audioText = "同学", options = listOf("同学", "朋友", "学生", "老师"), correctAnswer = "同学", explanation = "同学 (tóngxué) = Bạn cùng lớp.")
      )
    ),
    MicroLesson(
      id = "w2_n2_l3",
      nodeId = "w2_n2",
      title = "Ghép Câu Bạn Bè",
      subtitle = "Tạo câu: 他是我的朋友 & 我也是学生",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n2_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_pengyou"), "Ghép câu: \"Anh ấy là bạn của tôi.\"", sentenceWords = listOf("他", "是", "我的", "朋友", "也"), targetSentence = "他是我的朋友", correctAnswer = "他是我的朋友"),
        LearningActivity("w2_n2_a5", ActivityType.FILL_BLANK, SkillType.GRAMMAR, listOf("w2_ye"), "Điền từ còn thiếu: \"Tôi cũng là học sinh.\"", hanziPrompt = "我___是学生。", options = listOf("也", "很", "叫", "好"), correctAnswer = "也", explanation = "也 (yě) = Cũng.")
      )
    ),
    MicroLesson(
      id = "w2_n2_l4",
      nodeId = "w2_n2",
      title = "Hội Thoại Giao Lưu",
      subtitle = "Trò chuyện làm quen bạn mới cùng Gấu Trúc",
      type = "panda_conversation",
      order = 4,
      activities = listOf(
        LearningActivity("w2_n2_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_renshi"), "Gấu trúc nói với bạn:", pandaDialogue = "很高兴认识你！", options = listOf("我也很高兴认识你！", "我是老师。", "再见！"), correctAnswer = "我也很高兴认识你！", explanation = "Lời đáp lịch thiệp khi làm quen bạn mới.")
      )
    ),
    MicroLesson(
      id = "w2_n2_l5",
      nodeId = "w2_n2",
      title = "Luyện Nói & Viết",
      subtitle = "Nói câu giới thiệu bạn bè và viết chữ Hán",
      type = "speaking_writing",
      order = 5,
      activities = listOf(
        LearningActivity("w2_n2_a7", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w2_pengyou"), "Đọc câu giới thiệu bạn bè:", hanziPrompt = "他是我的朋友。", pinyinPrompt = "tā shì wǒ de péngyou.", audioText = "他是我的朋友", correctAnswer = "他是我的朋友"),
        LearningActivity("w2_n2_a8", ActivityType.WRITING, SkillType.WRITING, listOf("w2_pengyou"), "Gõ chữ Hán cho \"péngyou\":", pinyinPrompt = "péngyou", correctAnswer = "朋友", explanation = "朋友 = Bạn bè.")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 3: HOW OLD ARE YOU? (你几岁？)
  // -------------------------------------------------------------
  private val node3Lessons = listOf(
    MicroLesson(
      id = "w2_n3_l1",
      nodeId = "w2_n3",
      title = "Khám Phá Tuổi Tác",
      subtitle = "Học cách hỏi và trả lời tuổi bằng tiếng Trung",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n3_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_sui", "w2_ji", "w2_duoda", "w2_jinnian", "w2_shiba", "w2_ershi"), "Khám phá từ vựng tuổi tác", audioText = "岁 几 多大 今年 十八 二十", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n3_l2",
      nodeId = "w2_n3",
      title = "Luyện Nghe Nhận Diện Tuổi",
      subtitle = "Lắng nghe độ tuổi và chọn số chính xác",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n3_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_shiba"), "Nghe và chọn độ tuổi đúng:", audioText = "十八岁", options = listOf("shíbā suì (18 tuổi)", "èrshí suì (20 tuổi)", "shí suì (10 tuổi)", "bā suì (8 tuổi)"), correctAnswer = "shíbā suì (18 tuổi)"),
        LearningActivity("w2_n3_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_ershi"), "Nghe phát âm và chọn chữ Hán:", audioText = "二十岁", options = listOf("二十岁", "十八岁", "八岁", "十二岁"), correctAnswer = "二十岁")
      )
    ),
    MicroLesson(
      id = "w2_n3_l3",
      nodeId = "w2_n3",
      title = "Ghép Câu Nói Tuổi",
      subtitle = "Tạo câu: 我十八岁 & 我今年二十岁",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n3_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_sui"), "Ghép câu: \"Tôi mười tám tuổi.\"", sentenceWords = listOf("我", "十八", "岁", "今年", "是"), targetSentence = "我十八岁", correctAnswer = "我十八岁"),
        LearningActivity("w2_n3_a5", ActivityType.FILL_BLANK, SkillType.GRAMMAR, listOf("w2_duoda"), "Điền từ hỏi tuổi người lớn:", hanziPrompt = "你今年___？", options = listOf("多大", "几岁", "什么", "哪国"), correctAnswer = "多大", explanation = "你今年多大？ dùng hỏi tuổi lịch sự.")
      )
    ),
    MicroLesson(
      id = "w2_n3_l4",
      nodeId = "w2_n3",
      title = "Hội Thoại & Luyện Nói",
      subtitle = "Hỏi đáp tuổi tác cùng bạn Gấu Trúc",
      type = "conversation_speaking",
      order = 4,
      activities = listOf(
        LearningActivity("w2_n3_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_ji"), "Gấu trúc hỏi bạn:", pandaDialogue = "你几岁？", options = listOf("我十八岁。", "我叫 {{learner.name}}。", "我很好。"), correctAnswer = "我十八岁。"),
        LearningActivity("w2_n3_a7", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w2_ershi"), "Nhấn mic và nói:", hanziPrompt = "我今年二十岁。", pinyinPrompt = "wǒ jīnnián èrshí suì.", audioText = "我今年二十岁", correctAnswer = "我今年二十岁")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 4: WHAT DO YOU DO? (你做什么？)
  // -------------------------------------------------------------
  private val node4Lessons = listOf(
    MicroLesson(
      id = "w2_n4_l1",
      nodeId = "w2_n4",
      title = "Khám Phá Nghề Nghiệp",
      subtitle = "Học từ vựng bác sĩ, giáo viên, học sinh, sinh viên",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n4_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_zuo", "w2_gongzuo", "w2_xuesheng", "w2_laoshi", "w2_yisheng", "w2_daxuesheng"), "Khám phá nghề nghiệp phổ biến", audioText = "做 工作 学生 老师 医生 大学生", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n4_l2",
      nodeId = "w2_n4",
      title = "Luyện Nghe Nghề Nghiệp",
      subtitle = "Phân biệt giáo viên (老师) và bác sĩ (医生)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n4_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_laoshi"), "Nghe và chọn Pinyin nghề nghiệp:", audioText = "老师", options = listOf("lǎoshī", "yīshēng", "xuésheng", "gōngzuò"), correctAnswer = "lǎoshī"),
        LearningActivity("w2_n4_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_yisheng"), "Nghe và chọn chữ Hán tương ứng:", audioText = "医生", options = listOf("医生", "老师", "学生", "朋友"), correctAnswer = "医生")
      )
    ),
    MicroLesson(
      id = "w2_n4_l3",
      nodeId = "w2_n4",
      title = "Ghép Câu Nghề Nghiệp",
      subtitle = "Tạo câu: 我是学生 & 他是医生",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n4_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_xuesheng"), "Ghép câu: \"Tôi là học sinh.\"", sentenceWords = listOf("我", "是", "学生", "老师", "做"), targetSentence = "我是学生", correctAnswer = "我是学生"),
        LearningActivity("w2_n4_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_zuo"), "Gấu trúc hỏi nghề nghiệp của bạn:", pandaDialogue = "你做什么工作？", options = listOf("我是学生。", "我喝水。", "再见！"), correctAnswer = "我是学生。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 5: I LIKE (我喜欢)
  // -------------------------------------------------------------
  private val node5Lessons = listOf(
    MicroLesson(
      id = "w2_n5_l1",
      nodeId = "w2_n5",
      title = "Khám Phá Sở Thích",
      subtitle = "Bày tỏ thích và không thích âm nhạc, trà, cà phê",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n5_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_xihuan", "w2_buxihuan", "w2_yinyue", "w2_cha", "w2_kafei", "w2_kan", "w2_ting", "w2_chi", "w2_he"), "Khám phá từ vựng sở thích & ẩm thực", audioText = "喜欢 不喜欢 音乐 茶 咖啡 看 听 吃 喝", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n5_l2",
      nodeId = "w2_n5",
      title = "Luyện Nghe & Đọc",
      subtitle = "Nhận diện từ vựng thích (喜欢) và âm nhạc (音乐)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n5_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_xihuan"), "Nghe và chọn Pinyin của \"Thích\":", audioText = "喜欢", options = listOf("xǐhuan", "bù xǐhuan", "yīnyuè", "kāfēi"), correctAnswer = "xǐhuan"),
        LearningActivity("w2_n5_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_cha"), "Nghe và chọn chữ Hán cho \"Trà\":", audioText = "茶", options = listOf("茶", "咖啡", "水", "饭"), correctAnswer = "茶")
      )
    ),
    MicroLesson(
      id = "w2_n5_l3",
      nodeId = "w2_n5",
      title = "Ghép Câu Sở Thích",
      subtitle = "Tạo câu: 我喜欢中文 & 我不喜欢咖啡",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n5_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_xihuan"), "Ghép câu: \"Tôi thích tiếng Trung.\"", sentenceWords = listOf("我", "喜欢", "中文", "咖啡", "不"), targetSentence = "我喜欢中文", correctAnswer = "我喜欢中文"),
        LearningActivity("w2_n5_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_xihuan"), "Gấu trúc hỏi bạn:", pandaDialogue = "你喜欢中文吗？", options = listOf("我喜欢中文！", "我是老师。", "他是我哥哥。"), correctAnswer = "我喜欢中文！")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 6: WHAT DAY IS IT? (今天星期几？)
  // -------------------------------------------------------------
  private val node6Lessons = listOf(
    MicroLesson(
      id = "w2_n6_l1",
      nodeId = "w2_n6",
      title = "Khám Phá Các Thứ Trong Tuần",
      subtitle = "Học các ngày từ Thứ Hai đến Chủ Nhật và hôm nay/ngày mai/hôm qua",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n6_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_jintian", "w2_mingtian", "w2_zuotian", "w2_xingqiyi", "w2_xingqier", "w2_xingqisan", "w2_xingqisi", "w2_xingqiwu", "w2_xingqiliu", "w2_xingqiri"), "Khám phá các ngày trong tuần", audioText = "今天 明天 昨天 星期一 星期二 星期三 星期四 星期五 星期六 星期日", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n6_l2",
      nodeId = "w2_n6",
      title = "Luyện Nghe Các Ngày",
      subtitle = "Lắng nghe và nhận diện Thứ Sáu (星期五)",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n6_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_xingqiwu"), "Nghe và chọn Pinyin đúng:", audioText = "星期五", options = listOf("xīngqī wǔ (Thứ 6)", "xīngqī sì (Thứ 5)", "xīngqī liù (Thứ 7)", "xīngqī yī (Thứ 2)"), correctAnswer = "xīngqī wǔ (Thứ 6)"),
        LearningActivity("w2_n6_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_jintian"), "Nghe phát âm và chọn chữ Hán của \"Hôm nay\":", audioText = "今天", options = listOf("今天", "明天", "昨天", "星期日"), correctAnswer = "今天")
      )
    ),
    MicroLesson(
      id = "w2_n6_l3",
      nodeId = "w2_n6",
      title = "Ghép Câu & Hội Thoại Lịch Trình",
      subtitle = "Hỏi đáp: 今天星期几？ 今天星期五。",
      type = "conversation_sentence",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n6_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_xingqiwu"), "Ghép câu: \"Hôm nay là thứ Sáu.\"", sentenceWords = listOf("今天", "星期五", "明天", "是", "几"), targetSentence = "今天星期五", correctAnswer = "今天星期五"),
        LearningActivity("w2_n6_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_jintian"), "Gấu trúc hỏi bạn:", pandaDialogue = "今天星期几？", options = listOf("今天星期五。", "现在三点。", "我十八岁。"), correctAnswer = "今天星期五。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 7: WHAT TIME IS IT? (现在几点？)
  // -------------------------------------------------------------
  private val node7Lessons = listOf(
    MicroLesson(
      id = "w2_n7_l1",
      nodeId = "w2_n7",
      title = "Khám Phá Giờ Giấc",
      subtitle = "Học cách hỏi và xem giờ, phút, giờ rưỡi",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n7_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_xianzai", "w2_jidian", "w2_dian", "w2_fen", "w2_ban"), "Khám phá từ vựng thời gian & đồng hồ", audioText = "现在 几点 点 分 半", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n7_l2",
      nodeId = "w2_n7",
      title = "Luyện Nghe Giờ",
      subtitle = "Nghe các mốc giờ 3:00 và 3:30",
      type = "listen_pinyin",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n7_a2", ActivityType.LISTEN_PINYIN, SkillType.LISTENING, listOf("w2_ban"), "Nghe và chọn giờ chính xác:", audioText = "三点半", options = listOf("sān diǎn bàn (3:30)", "sān diǎn (3:00)", "bā diǎn (8:00)", "shí diǎn (10:00)"), correctAnswer = "sān diǎn bàn (3:30)"),
        LearningActivity("w2_n7_a3", ActivityType.LISTEN_HANZI, SkillType.READING, listOf("w2_xianzai"), "Nghe và chọn chữ Hán cho \"Bây giờ\":", audioText = "现在", options = listOf("现在", "今天", "明天", "几点"), correctAnswer = "现在")
      )
    ),
    MicroLesson(
      id = "w2_n7_l3",
      nodeId = "w2_n7",
      title = "Ghép Câu & Xem Giờ",
      subtitle = "Tạo câu: 现在三点半",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n7_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_dian"), "Ghép câu: \"Bây giờ là 3 giờ rưỡi.\"", sentenceWords = listOf("现在", "三点", "半", "几点", "今天"), targetSentence = "现在三点半", correctAnswer = "现在三点半"),
        LearningActivity("w2_n7_a5", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_jidian"), "Gấu trúc hỏi giờ:", pandaDialogue = "现在几点？", options = listOf("现在八点。", "今天星期一。", "我叫 {{learner.name}}。"), correctAnswer = "现在八点。")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 8: MY DAY (我的一天) - INTEGRATION NODE
  // -------------------------------------------------------------
  private val node8Lessons = listOf(
    MicroLesson(
      id = "w2_n8_l1",
      nodeId = "w2_n8",
      title = "Khám Phá Lịch Trình Một Ngày",
      subtitle = "Thức dậy, ăn cơm, học bài, đi làm và đi ngủ",
      type = "discover",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n8_a1", ActivityType.DISCOVER, SkillType.VOCABULARY, listOf("w2_zaoshang", "w2_qichuang", "w2_chifan", "w2_xuexi", "w2_huijia", "w2_shuijiao", "w2_wanshang", "w2_zai"), "Khám phá thói quen hàng ngày", audioText = "早上 起床 吃饭 学习 回家 睡觉 晚上 在", correctAnswer = "")
      )
    ),
    MicroLesson(
      id = "w2_n8_l2",
      nodeId = "w2_n8",
      title = "Nghe Trọn Vẹn Một Ngày",
      subtitle = "Lắng nghe các hoạt động từ sáng đến tối",
      type = "listen_sequence",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n8_a2", ActivityType.LISTEN_HANZI, SkillType.LISTENING, listOf("w2_qichuang"), "Nghe và chọn hành động buổi sáng:", audioText = "我早上起床", options = listOf("我早上起床", "我晚上睡觉", "我吃饭", "我喝茶"), correctAnswer = "我早上起床"),
        LearningActivity("w2_n8_a3", ActivityType.LISTEN_HANZI, SkillType.LISTENING, listOf("w2_xuexi"), "Nghe và chọn hoạt động học tập:", audioText = "我在学习中文", options = listOf("我在学习中文", "我在睡觉", "我是医生", "我叫 {{learner.name}}"), correctAnswer = "我在学习中文")
      )
    ),
    MicroLesson(
      id = "w2_n8_l3",
      nodeId = "w2_n8",
      title = "Ghép Chuỗi Hoạt Động",
      subtitle = "Tạo câu miêu tả thói quen hàng ngày hoàn chỉnh",
      type = "sentence_builder",
      order = 3,
      activities = listOf(
        LearningActivity("w2_n8_a4", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_xuexi"), "Ghép câu: \"Buổi sáng tôi học tiếng Trung.\"", sentenceWords = listOf("我", "早上", "学习", "中文", "睡觉", "晚上"), targetSentence = "我早上学习中文", correctAnswer = "我早上学习中文"),
        LearningActivity("w2_n8_a5", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_shuijiao"), "Ghép câu: \"Buổi tối tôi đi ngủ.\"", sentenceWords = listOf("我", "晚上", "睡觉", "起床", "在"), targetSentence = "我晚上睡觉", correctAnswer = "我晚上睡觉")
      )
    ),
    MicroLesson(
      id = "w2_n8_l4",
      nodeId = "w2_n8",
      title = "Hội Thoại Toàn Diện Với Gấu Trúc",
      subtitle = "Kể về một ngày năng động của bạn",
      type = "panda_conversation",
      order = 4,
      activities = listOf(
        LearningActivity("w2_n8_a6", ActivityType.PANDA_CONVERSATION, SkillType.CONVERSATION, listOf("w2_zai"), "Gấu trúc hỏi bạn đang làm gì:", pandaDialogue = "你在做什么？", options = listOf("我在学习中文。", "现在八点。", "这是我爸爸。"), correctAnswer = "我在学习中文。"),
        LearningActivity("w2_n8_a7", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w2_xuexi"), "Đọc câu tự giới thiệu lịch trình:", hanziPrompt = "我早上起床，我在学习中文。", pinyinPrompt = "wǒ zǎoshang qǐchuáng, wǒ zài xuéxí Zhōngwén.", audioText = "我早上起床，我在学习中文", correctAnswer = "我早上起床，我在学习中文")
      )
    )
  )

  // -------------------------------------------------------------
  // NODE 9: MY LIFE BOSS (我的生活挑战) - BOSS MASTERY CHALLENGE
  // -------------------------------------------------------------
  private val node9Lessons = listOf(
    MicroLesson(
      id = "w2_n9_l1",
      nodeId = "w2_n9",
      title = "Đấu Trường Cuộc Sống - Vòng 1 đến 5",
      subtitle = "Thử thách phản xạ giao tiếp tự nhiên về đời sống",
      type = "boss_challenge",
      order = 1,
      activities = listOf(
        LearningActivity("w2_n9_a1", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_jiaren"), "Vòng 1: Gấu trúc hỏi tên bạn:", pandaDialogue = "你叫什么名字？", options = listOf("我叫 {{learner.name}}。", "这是我爸爸。", "今天星期五。"), correctAnswer = "我叫 {{learner.name}}。", roundNumber = 1),
        LearningActivity("w2_n9_a2", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_sui"), "Vòng 2: Hỏi tuổi tác:", pandaDialogue = "你几岁？", options = listOf("我十八岁。", "我是学生。", "我很好。"), correctAnswer = "我十八岁。", roundNumber = 2),
        LearningActivity("w2_n9_a3", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_xuesheng"), "Vòng 3: Hỏi nghề nghiệp:", pandaDialogue = "你是学生吗？", options = listOf("是的，我是学生。", "我不喜欢咖啡。", "现在八点。"), correctAnswer = "是的，我是学生。", roundNumber = 3),
        LearningActivity("w2_n9_a4", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_xihuan"), "Vòng 4: Hỏi sở thích:", pandaDialogue = "你喜欢中文吗？", options = listOf("我非常喜欢中文！", "明天见。", "他是我朋友。"), correctAnswer = "我非常喜欢中文！", roundNumber = 4),
        LearningActivity("w2_n9_a5", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_xingqiwu"), "Vòng 5: Hỏi thứ trong tuần:", pandaDialogue = "今天星期几？", options = listOf("今天星期五。", "现在三点半。", "我早上吃饭。"), correctAnswer = "今天星期五。", roundNumber = 5)
      )
    ),
    MicroLesson(
      id = "w2_n9_l2",
      nodeId = "w2_n9",
      title = "Đấu Trường Cuộc Sống - Vòng 6 đến 10",
      subtitle = "Luyện nói, đọc hiểu và viết đỉnh cao World 2",
      type = "boss_challenge",
      order = 2,
      activities = listOf(
        LearningActivity("w2_n9_a6", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_jidian"), "Vòng 6: Hỏi giờ:", pandaDialogue = "现在几点？", options = listOf("现在八点半。", "我是大学生。", "谢谢！"), correctAnswer = "现在八点半。", roundNumber = 6),
        LearningActivity("w2_n9_a7", ActivityType.BOSS_ROUND, SkillType.CONVERSATION, listOf("w2_zai"), "Vòng 7: Hỏi hoạt động hiện tại:", pandaDialogue = "你在做什么？", options = listOf("我在学习中文。", "今天星期六。", "这是我妈妈。"), correctAnswer = "我在学习中文。", roundNumber = 7),
        LearningActivity("w2_n9_a8", ActivityType.SPEAKING, SkillType.SPEAKING, listOf("w2_xuexi"), "Vòng 8 (Nói): Thuyết trình một ngày của bạn:", hanziPrompt = "我早上起床，我学习中文，晚上睡觉。", pinyinPrompt = "wǒ zǎoshang qǐchuáng, wǒ xuéxí Zhōngwén, wǎnshang shuìjiào.", audioText = "我早上起床，我学习中文，晚上睡觉", correctAnswer = "我早上起床，我学习中文，晚上睡觉", roundNumber = 8),
        LearningActivity("w2_n9_a9", ActivityType.WRITING, SkillType.WRITING, listOf("w2_xihuan"), "Vòng 9 (Viết): Gõ chữ Hán cho \"xǐhuan\":", pinyinPrompt = "xǐhuan", correctAnswer = "喜欢", roundNumber = 9),
        LearningActivity("w2_n9_a10", ActivityType.SENTENCE_BUILDER, SkillType.GRAMMAR, listOf("w2_xihuan"), "Vòng 10 (Ghép câu cuối): Ghép câu hoàn chỉnh:", sentenceWords = listOf("我", "喜欢", "我的", "朋友", "也", "是"), targetSentence = "我喜欢我的朋友", correctAnswer = "我喜欢我的朋友", roundNumber = 10)
      )
    )
  )

  // -------------------------------------------------------------
  // ALL WORLD 2 NODE COURSES
  // -------------------------------------------------------------
  val world2NodeCourses = listOf(
    NodeCourseData(
      nodeId = "w2_n1",
      title = "My Family",
      subtitle = "我的家人 • Bố, mẹ, anh, chị, em",
      description = "Giới thiệu tất cả thành viên trong gia đình và làm quen với đại từ 他 / 她.",
      order = 1,
      vocabulary = listOf(
        itemMap["w2_jia"]!!,
        itemMap["w2_jiaren"]!!,
        itemMap["w2_baba"]!!,
        itemMap["w2_mama"]!!,
        itemMap["w2_gege"]!!,
        itemMap["w2_jiejie"]!!,
        itemMap["w2_didi"]!!,
        itemMap["w2_meimei"]!!
      ),
      microLessons = node1Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n2",
      title = "My Friends",
      subtitle = "我的朋友 • Bạn bè & Bạn cùng lớp",
      description = "Nói về mối quan hệ xã hội: 朋友, 同学, 也, 很 và mẫu câu 很高兴认识你.",
      order = 2,
      vocabulary = listOf(
        itemMap["w2_pengyou"]!!,
        itemMap["w2_tongxue"]!!,
        itemMap["w2_xuesheng"]!!,
        itemMap["w2_renshi"]!!,
        itemMap["w2_ye"]!!,
        itemMap["w2_hen"]!!
      ),
      microLessons = node2Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n3",
      title = "How Old Are You?",
      subtitle = "你几岁？ • Tuổi tác & Số đếm thực tế",
      description = "Hỏi và nói tuổi tác chính xác: 你几岁？ 我十八岁。 你多大？ 我二十岁。",
      order = 3,
      vocabulary = listOf(
        itemMap["w2_sui"]!!,
        itemMap["w2_ji"]!!,
        itemMap["w2_duoda"]!!,
        itemMap["w2_jinnian"]!!,
        itemMap["w2_shiba"]!!,
        itemMap["w2_ershi"]!!
      ),
      microLessons = node3Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n4",
      title = "What Do You Do?",
      subtitle = "你做什么？ • Nghề nghiệp & Công việc",
      description = "Giới thiệu nghề nghiệp: 学生, 老师, 医生, 大学生 và mẫu câu 你做什么工作？",
      order = 4,
      vocabulary = listOf(
        itemMap["w2_zuo"]!!,
        itemMap["w2_gongzuo"]!!,
        itemMap["w2_xuesheng"]!!,
        itemMap["w2_laoshi"]!!,
        itemMap["w2_yisheng"]!!,
        itemMap["w2_daxuesheng"]!!
      ),
      microLessons = node4Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n5",
      title = "I Like",
      subtitle = "我喜欢 • Sở thích, Trà, Cà phê & Âm nhạc",
      description = "Bày tỏ yêu thích và không thích: 我喜欢中文, 我喜欢喝茶, 我不喜欢咖啡.",
      order = 5,
      vocabulary = listOf(
        itemMap["w2_xihuan"]!!,
        itemMap["w2_buxihuan"]!!,
        itemMap["w2_yinyue"]!!,
        itemMap["w2_cha"]!!,
        itemMap["w2_kafei"]!!,
        itemMap["w2_chi"]!!,
        itemMap["w2_he"]!!
      ),
      microLessons = node5Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n6",
      title = "What Day Is It?",
      subtitle = "今天星期几？ • Các ngày trong tuần",
      description = "Nắm vững Thứ 2 đến Chủ Nhật, hôm nay, ngày mai và hôm qua.",
      order = 6,
      vocabulary = listOf(
        itemMap["w2_jintian"]!!,
        itemMap["w2_mingtian"]!!,
        itemMap["w2_zuotian"]!!,
        itemMap["w2_xingqiyi"]!!,
        itemMap["w2_xingqier"]!!,
        itemMap["w2_xingqisan"]!!,
        itemMap["w2_xingqisi"]!!,
        itemMap["w2_xingqiwu"]!!,
        itemMap["w2_xingqiliu"]!!,
        itemMap["w2_xingqiri"]!!
      ),
      microLessons = node6Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n7",
      title = "What Time Is It?",
      subtitle = "现在几点？ • Xem giờ & Phút",
      description = "Hỏi và đọc giờ chuẩn xác: 现在几点？ 现在三点。 现在三点半。",
      order = 7,
      vocabulary = listOf(
        itemMap["w2_xianzai"]!!,
        itemMap["w2_jidian"]!!,
        itemMap["w2_dian"]!!,
        itemMap["w2_fen"]!!,
        itemMap["w2_ban"]!!
      ),
      microLessons = node7Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n8",
      title = "My Day",
      subtitle = "我的一天 • Lịch trình sinh hoạt tích hợp",
      description = "Tổng hợp thói quen một ngày: 起床, 吃饭, 学习中文, 睡觉.",
      order = 8,
      vocabulary = listOf(
        itemMap["w2_zaoshang"]!!,
        itemMap["w2_qichuang"]!!,
        itemMap["w2_chifan"]!!,
        itemMap["w2_xuexi"]!!,
        itemMap["w2_huijia"]!!,
        itemMap["w2_shuijiao"]!!,
        itemMap["w2_zai"]!!
      ),
      microLessons = node8Lessons
    ),
    NodeCourseData(
      nodeId = "w2_n9",
      title = "My Life Challenge",
      subtitle = "我的生活挑战 • Đấu trường Bậc thầy Cuộc sống",
      description = "10 vòng thử thách tích hợp toàn bộ kiến thức World 2 từ tên, tuổi, bạn bè đến lịch trình.",
      order = 9,
      vocabulary = listOf(
        itemMap["w2_jiaren"]!!,
        itemMap["w2_pengyou"]!!,
        itemMap["w2_xuesheng"]!!,
        itemMap["w2_xihuan"]!!,
        itemMap["w2_xingqiwu"]!!,
        itemMap["w2_xianzai"]!!,
        itemMap["w2_xuexi"]!!
      ),
      microLessons = node9Lessons
    )
  )

  fun getNodeCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    val course = world2NodeCourses.find { it.nodeId == nodeId } ?: return null
    return if (learnerName.isNotBlank()) course.interpolateLearner(learnerName) else course
  }
}
