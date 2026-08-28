package com.example.model

enum class SkillType(val displayName: String) {
  VOCABULARY("Từ vựng"),
  LISTENING("Luyện nghe"),
  READING("Luyện đọc"),
  SPEAKING("Luyện nói"),
  PRONUNCIATION("Phát âm"),
  WRITING("Tập viết"),
  GRAMMAR("Ngữ pháp"),
  CONVERSATION("Hội thoại")
}

data class SkillProgress(
  val type: SkillType,
  val percentage: Int = 0
)

enum class NodeIconType {
  HOME_BASE,
  HELLO_GATE,
  TONE_GAME,
  PANDA_FRIEND,
  MY_NAME,
  MY_COUNTRY,
  NUMBERS,
  FIRST_CONVERSATION,
  FIRST_CONVERSATION_BOSS,
  GENERAL_LESSON,
  BOSS_CHALLENGE
}

data class JourneyNode(
  val id: String,
  val name: String,
  val iconType: NodeIconType,
  val isCurrent: Boolean = false,
  val isLocked: Boolean = true,
  val isCompleted: Boolean = false,
  val pinyin: String = "",
  val chinese: String = "",
  val englishSummary: String = "",
  val worldNumber: Int = 1
)

data class WorldData(
  val id: String,
  val number: Int,
  val chinese: String,
  val english: String,
  val description: String,
  val iconEmoji: String,
  val isCompleted: Boolean = false,
  val isCurrent: Boolean = false,
  val isLocked: Boolean = true,
  val nodes: List<JourneyNode> = emptyList()
)

data class AchievementItem(
  val id: String,
  val title: String,
  val iconLabel: String,
  val isUnlocked: Boolean = false
)

data class LearnerState(
  val name: String = "",
  val country: String = "Việt Nam",
  val level: Int = 1,
  val xp: Int = 0,
  val dailyGoalTarget: Int = 10,
  val dailyGoalCurrent: Int = 0,
  val streakDays: Int = 0,
  val currentWorldId: String = "world_1",
  val currentNodeId: String = "home_base",
  val skills: Map<SkillType, Int> = mapOf(
    SkillType.VOCABULARY to 0,
    SkillType.LISTENING to 0,
    SkillType.SPEAKING to 0,
    SkillType.PRONUNCIATION to 0,
    SkillType.GRAMMAR to 0
  ),
  val soundEffectsEnabled: Boolean = true,
  val remindersEnabled: Boolean = true,
  val worlds: List<WorldData> = defaultWorldsData,
  val journeyNodes: List<JourneyNode> = defaultWorldsData.first().nodes,
  val achievements: List<AchievementItem> = initialAchievements,
  val isOnboardingCompleted: Boolean = false,
  val avatar: String = "dudu"
)

val initialWorld1Nodes = listOf(
  JourneyNode(
    id = "w1_n1",
    name = "Khởi Đầu",
    iconType = NodeIconType.HOME_BASE,
    isCurrent = true,
    isLocked = false,
    isCompleted = false,
    pinyin = "Nǐ hǎo",
    chinese = "你好",
    englishSummary = "Chào mừng bạn đến với hành trình! Hãy bắt đầu bằng lời chào.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n2",
    name = "Cổng Chào",
    iconType = NodeIconType.HELLO_GATE,
    isCurrent = false,
    isLocked = true,
    pinyin = "Nǐ hǎo ma",
    chinese = "你好吗",
    englishSummary = "Vượt qua cổng Vạn Lý Trường Thành.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n3",
    name = "Thanh Điệu",
    iconType = NodeIconType.TONE_GAME,
    isCurrent = false,
    isLocked = true,
    pinyin = "Shēngdiào",
    chinese = "声调",
    englishSummary = "Làm chủ 4 thanh điệu trong tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n4",
    name = "Bạn Gấu Trúc",
    iconType = NodeIconType.PANDA_FRIEND,
    isCurrent = false,
    isLocked = true,
    pinyin = "Xióngmāo",
    chinese = "熊猫",
    englishSummary = "Gặp gỡ Bảo Bảo - chú gấu trúc đồng hành.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n5",
    name = "Tên Của Tôi",
    iconType = NodeIconType.MY_NAME,
    isCurrent = false,
    isLocked = true,
    pinyin = "Wǒ jiào...",
    chinese = "我叫...",
    englishSummary = "Giới thiệu họ tên bằng tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n6",
    name = "Quốc Gia",
    iconType = NodeIconType.MY_COUNTRY,
    isCurrent = false,
    isLocked = true,
    pinyin = "Guójiā",
    chinese = "国家",
    englishSummary = "Nói về quê hương và quốc tịch.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n7",
    name = "Chữ Số",
    iconType = NodeIconType.NUMBERS,
    isCurrent = false,
    isLocked = true,
    pinyin = "Yī, èr, sān...",
    chinese = "一二三",
    englishSummary = "Đếm từ 1 đến 10 bằng tiếng Trung.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n8",
    name = "Hội Thoại Đầu Tiên",
    iconType = NodeIconType.FIRST_CONVERSATION,
    isCurrent = false,
    isLocked = true,
    pinyin = "Duìhuà",
    chinese = "对话",
    englishSummary = "Cuộc trò chuyện thực tế đầu tiên của bạn.",
    worldNumber = 1
  ),
  JourneyNode(
    id = "w1_n9",
    name = "Thử Thách Bậc Thầy",
    iconType = NodeIconType.FIRST_CONVERSATION_BOSS,
    isCurrent = false,
    isLocked = true,
    pinyin = "Dàshī",
    chinese = "大师挑战",
    englishSummary = "Bài kiểm tra tổng hợp cùng Bậc Thầy Trường Thành.",
    worldNumber = 1
  )
)

val initialWorld2Nodes = listOf(
  JourneyNode("w2_n1", "Gia Đình Tôi", NodeIconType.GENERAL_LESSON, false, true, false, "Jiārén", "我的家人", "Giới thiệu các thành viên trong gia đình: bố, mẹ, anh, chị, em.", 2),
  JourneyNode("w2_n2", "Bạn Bè Của Tôi", NodeIconType.GENERAL_LESSON, false, true, false, "Péngyou", "我的朋友", "Nói về bạn bè, bạn học và quan hệ xã hội.", 2),
  JourneyNode("w2_n3", "Bạn Bao Nhiêu Tuổi?", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ jǐ suì?", "你几岁？", "Hỏi và nói về tuổi tác kết hợp số đếm.", 2),
  JourneyNode("w2_n4", "Bạn Làm Nghề Gì?", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ zuò shénme?", "你做什么？", "Nghề nghiệp: học sinh, giáo viên, bác sĩ.", 2),
  JourneyNode("w2_n5", "Tôi Thích...", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ xǐhuan", "我喜欢", "Bày tỏ sở thích: âm nhạc, tiếng Trung, trà, cà phê.", 2),
  JourneyNode("w2_n6", "Hôm Nay Thứ Mấy?", NodeIconType.GENERAL_LESSON, false, true, false, "Jīntiān xīngqī jǐ?", "今天星期几？", "Nói các ngày trong tuần từ Thứ Hai đến Chủ Nhật.", 2),
  JourneyNode("w2_n7", "Bây Giờ Mấy Giờ?", NodeIconType.GENERAL_LESSON, false, true, false, "Xiànzài jǐ diǎn?", "现在几点？", "Hỏi và nói giờ giấc chính xác trong ngày.", 2),
  JourneyNode("w2_n8", "Một Ngày Của Tôi", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ de yītiān", "我的一天", "Tổng hợp lịch trình từ sáng thức dậy đến tối đi ngủ.", 2),
  JourneyNode("w2_n9", "Thử Thách Cuộc Sống", NodeIconType.BOSS_CHALLENGE, false, true, false, "Shēnghuó tiǎozhàn", "我的生活挑战", "Trùm World 2: Đấu trường thử thách toàn diện.", 2)
)

val initialWorld3Nodes = listOf(
  JourneyNode("w3_n1", "Gia Đình Thân Yêu", NodeIconType.GENERAL_LESSON, false, true, false, "Jiārén", "我的家人", "Khám phá danh xưng cha mẹ, anh chị em.", 3),
  JourneyNode("w3_n2", "Anh Ấy Là Ai?", NodeIconType.GENERAL_LESSON, false, true, false, "Tā shì shéi?", "他是谁？", "Xác định danh tính người thân: 他是谁, 他是我爸爸.", 3),
  JourneyNode("w3_n3", "Nhà Có Mấy Người?", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ jiā yǒu jǐ gè rén?", "你家有几个人？", "Hỏi và đếm số lượng người trong gia đình.", 3),
  JourneyNode("w3_n4", "Độ Tuổi Gia Đình", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ jǐ suì?", "你几岁？", "Hỏi tuổi người thân và giới thiệu năm nay bao nhiêu tuổi.", 3),
  JourneyNode("w3_n5", "Ngôi Nhà Của Tôi", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ de jiā", "我的家", "Phòng khách, phòng ngủ, phòng bếp, nhà vệ sinh.", 3),
  JourneyNode("w3_n6", "Ở Đâu Thế?", NodeIconType.GENERAL_LESSON, false, true, false, "Zài nǎlǐ?", "在哪里？", "Vị trí đồ vật: sách, bàn, ghế, trong phòng.", 3),
  JourneyNode("w3_n7", "Miêu Tả Người Thân", NodeIconType.GENERAL_LESSON, false, true, false, "Míashù jiārén", "我的家人", "Tính từ miêu tả: cao, to, nhỏ, xinh đẹp, đáng yêu.", 3),
  JourneyNode("w3_n8", "Gia Đình & Nhà Cửa", NodeIconType.GENERAL_LESSON, false, true, false, "Jiātíng yǔ jiā", "我的家", "Hội thoại tích hợp kết hợp gia đình và không gian sống.", 3),
  JourneyNode("w3_n9", "Thử Thách Gia Đình", NodeIconType.BOSS_CHALLENGE, false, true, false, "Jiātíng tiǎozhàn", "我的家挑战", "Trùm World 3: Đấu trường thử thách gia đình và nhà cửa.", 3)
)

val initialWorld4Nodes = listOf(
  JourneyNode("w4_n1", "Trường Học", NodeIconType.GENERAL_LESSON, false, true, false, "Xuéxiào", "学校", "Khám phá trường học, đại học và phòng học.", 4),
  JourneyNode("w4_n2", "Bạn Cùng Lớp", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ de tóngxué", "我的同学", "Nói về bạn học nam, bạn học nữ và người quen.", 4),
  JourneyNode("w4_n3", "Thầy Cô & Học Sinh", NodeIconType.GENERAL_LESSON, false, true, false, "Lǎoshī hé xuésheng", "老师和学生", "Vai trò trong lớp: dạy học, học tập, vào lớp, tan lớp.", 4),
  JourneyNode("w4_n4", "Tôi Học Tiếng Trung", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ xuéxí Zhōngwén", "我学习中文", "Luyện 4 kỹ năng ngôn ngữ: Nghe, Nói, Đọc, Viết.", 4),
  JourneyNode("w4_n5", "Bạn Thích Môn Gì?", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ xǐhuan shénme kè?", "你喜欢什么课？", "Bày tỏ sở thích môn học: tiếng Trung, tiếng Anh, toán học.", 4),
  JourneyNode("w4_n6", "Cùng Nhau Học Tập", NodeIconType.GENERAL_LESSON, false, true, false, "Yìqǐ xuéxí", "一起学习", "Tương tác xã hội: rủ bạn cùng học, hẹn gặp, mời bạn.", 4),
  JourneyNode("w4_n7", "Bạn Ở Đâu Thế?", NodeIconType.GENERAL_LESSON, false, true, false, "Nǐ zài nǎr?", "你在哪儿？", "Vị trí trong trường: thư viện, nhà ăn, sân trường, lớp học.", 4),
  JourneyNode("w4_n8", "Đời Sống Học Đường", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ de xuéxiào shēnghuó", "我的学校生活", "Hội thoại tích hợp miêu tả một ngày học tập sôi động.", 4),
  JourneyNode("w4_n9", "Thử Thách Trường Học", NodeIconType.BOSS_CHALLENGE, false, true, false, "Xuéxiào tiǎozhàn", "学校挑战", "Trùm World 4: Đấu trường thử thách toàn diện đời sống học đường.", 4)
)

val initialWorld5Nodes = listOf(
  JourneyNode("w5_n1", "Chợ Bắc Kinh", NodeIconType.GENERAL_LESSON, false, true, false, "Běijīng shìchǎng", "北京市场", "Làm quen không gian chợ, cửa hàng, ông chủ, mua và bán.", 5),
  JourneyNode("w5_n2", "Tôi Muốn Mua...", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ xiǎng mǎi...", "我想买...", "Bày tỏ nhu cầu mua sắm: nước, trà, hoa quả, táo, sách.", 5),
  JourneyNode("w5_n3", "Bao Nhiêu Tiền?", NodeIconType.GENERAL_LESSON, false, true, false, "Duōshao qián?", "多少钱？", "Hỏi và nghe giá tiền: khối/tệ (块/元), đắt (贵), rẻ (便宜).", 5),
  JourneyNode("w5_n4", "Tôi Lấy Cái Này", NodeIconType.GENERAL_LESSON, false, true, false, "Wǒ yào zhège", "我要这个", "Lựa chọn món hàng: cái này (这个), cái kia (那个), đưa cho (给).", 5),
  JourneyNode("w5_n5", "Bao Nhiêu Cái?", NodeIconType.GENERAL_LESSON, false, true, false, "Duōshao gè?", "多少个？", "Lượng từ và số lượng: 1 cái, 2 cái, 3 cái, 5 cái quả táo.", 5),
  JourneyNode("w5_n6", "Đắt Quá Rồi", NodeIconType.GENERAL_LESSON, false, true, false, "Tài guì le", "太贵了", "Phản ứng giá cả: quá đắt (太贵了), rẻ một chút (便宜一点儿).", 5),
  JourneyNode("w5_n7", "Bớt Một Chút Đi", NodeIconType.GENERAL_LESSON, false, true, false, "Piányi yìdiǎnr", "便宜一点儿", "Thương lượng giá cả: có thể bớt không, 10 đồng được không.", 5),
  JourneyNode("w5_n8", "Mua Sắm Tại Chợ", NodeIconType.GENERAL_LESSON, false, true, false, "Shìchǎng gòuwù", "市场购物", "Mô phỏng thực tế mua sắm tại quầy hoa quả, đồ uống.", 5),
  JourneyNode("w5_n9", "Thử Thách Chợ Bắc Kinh", NodeIconType.BOSS_CHALLENGE, false, true, false, "Běijīng shìchǎng tiǎozhàn", "北京市场挑战", "Trùm World 5: Nhiệm vụ hoàn thành danh sách mua sắm thực tế.", 5)
)

val initialWorld6Nodes = listOf(
  JourneyNode("w6_n1", "Gọi Món Ăn", NodeIconType.GENERAL_LESSON, false, true, false, "Diǎncài", "点菜", "Gọi mì, há cảo và vịt quay Bắc Kinh.", 6),
  JourneyNode("w6_n2", "Hương Vị", NodeIconType.GENERAL_LESSON, false, true, false, "Suān tián kǔ là", "酸甜苦辣", "Hiểu vị cay, ngọt, mặn và chua.", 6),
  JourneyNode("w6_n3", "Trà & Tráng Miệng", NodeIconType.GENERAL_LESSON, false, true, false, "Chá yǔ tiándiǎn", "茶与甜点", "Văn hóa trà đạo và các món tráng miệng.", 6),
  JourneyNode("w6_n4", "Trùm Đại Tiệc", NodeIconType.BOSS_CHALLENGE, false, true, false, "Yànxí tiǎozhàn", "宴席挑战", "Chủ trì bữa tiệc ăn mừng thịnh soạn.", 6)
)

val initialWorld7Nodes = listOf(
  JourneyNode("w7_n1", "Địa Danh Thành Phố", NodeIconType.GENERAL_LESSON, false, true, false, "Dìbiāo", "城市地标", "Tìm ngân hàng, công viên, bảo tàng và cửa hàng.", 7),
  JourneyNode("w7_n2", "Hỏi Đường", NodeIconType.GENERAL_LESSON, false, true, false, "Wènlù", "问路", "Rẽ trái, rẽ phải và đi thẳng.", 7),
  JourneyNode("w7_n3", "Tàu Điện & Taxi", NodeIconType.GENERAL_LESSON, false, true, false, "Dìtiě yǔ chūzūchē", "地铁与出租车", "Di chuyển bằng phương tiện công cộng.", 7),
  JourneyNode("w7_n4", "Trùm Hướng Dẫn Viên", NodeIconType.BOSS_CHALLENGE, false, true, false, "Dǎoyóu tiǎozhàn", "导游挑战", "Hướng dẫn du khách khám phá thành phố.", 7)
)

val initialWorld8Nodes = listOf(
  JourneyNode("w8_n1", "Sân Bay & Tàu Cao Tốc", NodeIconType.GENERAL_LESSON, false, true, false, "Jīchǎng", "机场与高铁", "Lên tàu cao tốc và các chuyến bay.", 8),
  JourneyNode("w8_n2", "Nhận Phòng Khách Sạn", NodeIconType.GENERAL_LESSON, false, true, false, "Jiǔdiàn", "酒店入住", "Đặt phòng và yêu cầu dịch vụ tiện ích.", 8),
  JourneyNode("w8_n3", "Danh Lam Thắng Cảnh", NodeIconType.GENERAL_LESSON, false, true, false, "Guānguāng", "名胜古迹", "Tham quan công viên quốc gia và di sản.", 8),
  JourneyNode("w8_n4", "Trùm Thám Hiểm", NodeIconType.BOSS_CHALLENGE, false, true, false, "Lǚxíng dàshī", "旅行大师", "Lập kế hoạch du lịch xuyên quốc gia.", 8)
)

val initialWorld9Nodes = listOf(
  JourneyNode("w9_n1", "Tử Cấm Thành", NodeIconType.GENERAL_LESSON, false, true, false, "Gùgōng", "故宫探秘", "Khám phá hoàng cung và các triều đại.", 9),
  JourneyNode("w9_n2", "Đỉnh Trường Thành", NodeIconType.GENERAL_LESSON, false, true, false, "Chángchéng", "登长城", "Chạm tới ngọn tháp canh cao nhất.", 9),
  JourneyNode("w9_n3", "Ngõ Hẻm Hồ Đồng", NodeIconType.GENERAL_LESSON, false, true, false, "Hútòng", "胡同寻味", "Khám phá nét đẹp văn hóa ngõ cổ Bắc Kinh.", 9),
  JourneyNode("w9_n4", "Thử Thách Thần Long", NodeIconType.BOSS_CHALLENGE, false, true, false, "Lóngshén kǎohé", "终极龙神考核", "Bài thi khảo hạch tiếng Trung tối thượng.", 9)
)

val defaultWorldsData = listOf(
  WorldData(
    id = "world_1",
    number = 1,
    chinese = "你好！",
    english = "Lần Đầu Gặp Gỡ",
    description = "Pinyin, chào hỏi, thanh điệu, chữ số & giới thiệu bản thân",
    iconEmoji = "👋",
    isCompleted = false,
    isCurrent = true,
    isLocked = false,
    nodes = initialWorld1Nodes
  ),
  WorldData(
    id = "world_2",
    number = 2,
    chinese = "我的生活",
    english = "Cuộc Sống Của Tôi",
    description = "Thói quen hàng ngày, thời gian, ngày tháng & sở thích",
    iconEmoji = "☀️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld2Nodes
  ),
  WorldData(
    id = "world_3",
    number = 3,
    chinese = "我的家",
    english = "Ngôi Nhà Của Tôi",
    description = "Gia đình, các phòng, thú cưng & vật dụng trong nhà",
    iconEmoji = "🏡",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld3Nodes
  ),
  WorldData(
    id = "world_4",
    number = 4,
    chinese = "学校与朋友",
    english = "Trường Học & Bạn Bè",
    description = "Đời sống học đường, kết bạn & bày tỏ quan điểm",
    iconEmoji = "🎒",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld4Nodes
  ),
  WorldData(
    id = "world_5",
    number = 5,
    chinese = "北京市场",
    english = "Chợ Bắc Kinh",
    description = "Trái cây tươi, mặc cả, tiền tệ & thanh toán điện tử",
    iconEmoji = "🏮",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld5Nodes
  ),
  WorldData(
    id = "world_6",
    number = 6,
    chinese = "餐厅",
    english = "Nhà Hàng",
    description = "Gọi món, các loại hương vị ẩm thực & văn hóa trà đạo",
    iconEmoji = "🥢",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld6Nodes
  ),
  WorldData(
    id = "world_7",
    number = 7,
    chinese = "我的城市",
    english = "Thành Phố Của Tôi",
    description = "Địa danh thành phố, hỏi đường & phương tiện công cộng",
    iconEmoji = "🏙️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld7Nodes
  ),
  WorldData(
    id = "world_8",
    number = 8,
    chinese = "旅行",
    english = "Du Lịch",
    description = "Sân bay, đường sắt cao tốc, khách sạn & tham quan",
    iconEmoji = "✈️",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld8Nodes
  ),
  WorldData(
    id = "world_9",
    number = 9,
    chinese = "北京挑战",
    english = "Thử Thách Bắc Kinh",
    description = "Tử Cấm Thành, đỉnh Vạn Lý Trường Thành & kỳ thi Thần Long",
    iconEmoji = "🐉",
    isCompleted = false,
    isCurrent = false,
    isLocked = true,
    nodes = initialWorld9Nodes
  )
)

val initialAchievements = listOf(
  AchievementItem(
    id = "first_words",
    title = "Từ Vựng Đầu Tiên",
    iconLabel = "你好",
    isUnlocked = false
  ),
  AchievementItem(
    id = "first_conversation",
    title = "Hội Thoại Đầu Tiên",
    iconLabel = "💬",
    isUnlocked = false
  ),
  AchievementItem(
    id = "world_explorer",
    title = "Nhà Thám Hiểm Thế Giới",
    iconLabel = "🗺️",
    isUnlocked = false
  )
)
