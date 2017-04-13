package scy.com.myreader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyView extends View {

    private static final String TAG = "--main--";
    //画笔
    private Paint mPaint;

    private int nextFirstIndex;
    //行宽
    private int lineWidth = 960;

    //行高
    private int lineHeight = 55;

    //行间距
    private int lineSpace = 27;

    //文字的大小
    private int textSize = 55;

    private int startWord = 0;

    public MyView(Context context) {
        super(context);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawContent(canvas);
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) MyApplication.getMyApplication().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(textSize);
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static final int getAppHeight() {
        return MyApplication.getMyApplication().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static final int getAppWidth() {
        return MyApplication.getMyApplication().getResources().getDisplayMetrics().widthPixels;
    }


    private void drawContent(Canvas canvas) {
        String str = "为规范我校本科生学士学位论文（设计）格式，依据GB/7713-1987《科技报告、学位论文和学术论文的编写规格》，特制定本规范。\n" +
                "1 内容要求\n" +
                "学士学位论文（设计）内容一般应由十个主要部分组成，依次为：封面→中文摘要→英文摘要→目录→符号说明→论文正文→参考文献→附录→致谢→独创性声明。\n" +
                "1.1 题目、标题\n" +
                "准确概括论文核心内容，中文题目一般不宜超过20字，必要时可增加副标题，外文题目一般不宜超过10个实词，并按英文规范书写。\n" +
                "各级标题不能独立置于本页末端，各章标题不能接在上章尾端，应重新起页。\n" +
                "1.2 摘要与关键词\n" +
                "1.2.1 摘要\n" +
                "简要说明论文（设计）的主要内容、方法、结果及创新点。应有中文、外文两种文本，如无特殊情况，外文文本摘要一般使用英文撰写。中文、外文摘要各占一页A4纸。外文摘要要与中文摘要相呼应，其写作模式同中文基本相同。\n" +
                "中文摘要一般不超过300字，英文摘要不超过1500印刷符号，含中、英文摘要关键词。\n" +
                "1.2.2 关键词\n" +
                "关键词是供检索用的主题词条,应采用能覆盖论文主要内容的通用技术词条(参照相应的技术术语标准）。关键词一般列3～5个，按词条的外延层次从大到小排列。\n" +
                "1.3 目录\n" +
                "目录：按论文（设计）章、节、条次序编写，采用阿拉伯数字分级编号，要求层次清晰，设计图纸要有标号。目录中应包括绪论（引言、前言）、正文、参考文献、附录、致谢等。\n" +
                "1.4 论文正文\n" +
                "论文正文包括绪论、论文主体及结论等部分。\n" +
                "1.4.1 绪论（前言）\n" +
                "一般作为论文的第一章，是正文的开端，包括毕业论文（设计）的选题背景、目的及意义，国内外研究现状，对选题的研究设想、方案设计、主要结果和意义，论点鲜明、论据充分，论断有理有据。绪论不能与摘要雷同，不能成为摘要的注释。\n" +
                "1.4.2 主体\n" +
                "论文主体是学位论文的主要部分，应该结构合理，层次清楚，重点突出，文字简练、通顺。论文主体的内容应包括以下各方面：\n" +
                "研究内容的总体方案设计与选择论证；\n" +
                "研究内容各部分（包括硬件与软件）的设计计算；\n" +
                "研究内容试验方案设计的可行性、有效性以及试验数据处理及分析；\n" +
                "研究内容的理论分析。对本研究内容及成果应进行较全面、客观的理论阐述，应着重指出本研究内容中的创新、改进与实际应用之处。理论分析中，应将他人研究成果单独书写，并注明出处，不得将其与本人提出的理论分析混淆在一起。对于将其他领域的理论、结果引用到本研究领域者，应说明该理论的出处，并论述引用的可行性与有效性。\n" +
                "自然科学学科的论文应推理正确，结论明确，无科学性错误；管理人文学科论文应包括对研究问题的论述及系统分析，比较研究、模型或方案设计，案例论证或实证分析，模型运行的结果分析或建议、改进措施等。\n" +
                "须特别指出，在论文主体各章之后，“本章小结”要作为单独一节概括写出。\n" +
                "1.4.3 结论\n" +
                "可以总结性地说明学位论文的最终研究成果及其价值，同时，可以提出需要讨论的问题和建议。\n" +
                "结论应当体现作者更深的认识，且是从全篇论文的全部材料出发，经过推理、判断、归纳等逻辑分析过程而得到的新的学术总观念、总见解，不应当是正文中各段小结的简单重复。\n" +
                "1.5 参考文献\n" +
                "按照正文中出现的顺序列出直接引用的参考文献，论文的撰写应本着严谨求实的态度，凡引用他人成果之处均应列于参考文献中，并且只应列出正文以标注形式引用或参考的有关著作或论文。一篇论著在文中只引用一次时，只在论文后参考文献中，标出引用页数，如多次引用时，在正文中标注首次引用的文献序号，并在序号“[]”外著录引文页码。在参考文献中只应出现一次，序号以第一次出现位置为准。\n" +
                "学士学位论文的参考文献是作者写论文时所参考、引用的文献书目，引用文献的标示应置于所引内容最后一个字的右上角，所引文献编号用阿拉伯数字置于方括号“[ ]”中，用小4号字体的上角标，如“二次铣削[1]。”不得将引用文献标示置于各级标题处。\n" +
                "1.6 致谢\n" +
                "对导师和给予指导或协助完成学位论文工作的组织和个人表示感谢。内容应简洁明了、实事求是。\n" +
                "1.7 附录\n" +
                "附录是论文主体的补充项目，但不是必需的。未尽事宜可将其列在附录中加以说明。论文（设计）有关的数据表、符号说明、计算程序、运行结果、主要设备、仪器仪表的性能指标和测试分析结果、精度等均可列在附录中。\n" +
                "2 书写规范与打印要求\n" +
                "2.1 文字和字数\n" +
                "除外语专业外，一般用汉语简化字打印；研究型论文5000—7000字；综述型论文不少于10000字；外文类不少于20000字符；毕业（设计）作品类3000-5000字。\n" +
                "2.2 书写及装订\n" +
                "本科生毕业论文一律要求在计算机上输入、编排，使用A4标准纸单面打印，左侧装订。论文裁切后规格为16开纸(184mm*260mm).\n" +
                "2.3 字体和字号\n" +
                "·论文题目                                  2号黑体居中\n" +
                "·各章题序及标题                            3黑体居中\n" +
                "·各节一级题序及标题                        4号黑体左对齐\n" +
                "·各节二级题序及标题                        小4号黑体左对齐\n" +
                "·各节三级题序及标题                        小4号宋体加粗左对齐\n" +
                "·款、项                                    小4号黑体\n" +
                "·正文                                      小4号宋体\n" +
                "·摘要、结论、参考文献标题（中英文）        3号黑体居中\n" +
                "·摘要、参考文献内容      小4号宋体（英文Times New Roman体小4号）\n" +
                "·结论内容                                  小 4号宋体左对齐\n" +
                "·目录标题                                  3号黑体居中\n" +
                "·目录内容中章的标题                        小4号黑体左对齐\n" +
                "·目录中其他内容                            小4号宋体\n" +
                "·引言                                      小4号黑体居中\n" +
                "·论文页码                       页面低端居中、阿拉伯数字连续编码\n" +
                "·页眉与页脚                                  5号宋体居中\n" +
                "·论文中所出现的阿拉伯数字和字母             Times New Roman体5号\n" +
                "·附录、致谢                                  3号黑体居中\n" +
                "·独创性声明                                  3号黑体居中\n" +
                "·附录、致谢、独创性声明内容                  小4号宋体\n" +
                "2.4 封面 \n" +
                "封面排版见论文模板。\n" +
                "2.5 页面设置\n" +
                "2.5.1 页眉\n" +
                "页眉内容一律为“牡丹江师范学院学士学位论文（设计）”,字体为小5号宋体，页眉采用下边款直线，宽度为1/2磅。\n" +
                "2.5.2 页边距\n" +
                "页边距：上、下、左、右均为3cm,页眉2.3cm,页脚2cm。\n" +
                "2.5.3 行间距\n" +
                "正文行间距为1.5倍行距，段前、段后均为0行。 \n" +
                "2.5.4 页码的书写要求\n" +
                "论文页码从正文开始至参考文献结束，用阿拉伯数字连续编排，页码位于页面底端居中。封面、摘要、目录、致谢和独创性声明不编入论文页码。\n" +
                "2.6 摘要\n" +
                "2.6.1 中文摘要\n" +
                "中文摘要包括：摘要、摘要正文和关键词。摘要正文下空一行顶格打印“关键词”三字，每一个关键词之间用“;”分开，最后一个关键词不打标点符号。\n" +
                "2.6.2 外文摘要\n" +
                "外文（多用英文）摘要应另起一页，其内容及关键词应与中文摘要一致，并要符合外语语法习惯，语句通畅，文字流畅。\n" +
                "外文一律为 Times New Roman体，字号与正文摘要相对应。 \n" +
                "2.7 目录\n" +
                "目录的三级标题，建议按（1……，1.1……，1.1.1……）的格式编写；目录中各章节题序的阿拉伯数字用Times New Roman体。\n" +
                "2.8 正文\n" +
                "2.8.1 章节和各章标题\n" +
                "正文分章节撰写，每章结束后应另起一页。各章标题要突出重点，简明扼要。字数一般在15字以内，不得使用标点符号。标题中尽量不采用英文缩写词，必须采用时，应使用本行业的通用缩写词。\n" +
                "2.8.2 层次\n" +
                "层次以少为宜，根据需要进行选择。正文层次的编排和代号要求统一，层次如下：章（第1章）、节（如“1.1”）、条（如 “1.1.1”）、款（如“1、”）、项（如“（1）”），层次用到哪一层视需要而定，若节后无条可直接到“款”、“项”。 \n" +
                "2.9 引用文献\n" +
                "引用文献标示方式应全文统一，并采用所在学科领域内通用的方式，用上标的形式置于所引用内容最末句的右上角，用小四号字体。所引文献编号用阿拉伯数字置于方括号中，如：“……成果[1]”。当提及的参考文献为中文直接说明时，其序号应该用小4号字正文排齐，如“由文献[8,10-14]可知”。\n" +
                "不得将引用文献标示置于各级标题处。\n" +
                "2.10 名词术语\n" +
                "科技名词术语及设备、元件的名称，应采用国家标准或部颁标准中规定的术语或名称。标准中未规定的术语要采用行业通用术语或名称。全文名词术语必须统一。一些特殊名词或新名词应在适当位置加以说明或注解。\n" +
                "采用英语缩写词时，除本行业广泛应用的通用缩写词外，文中第一次出现的缩写词应该用括号注明英文原词。\n" +
                "2.11 物理量名称、符号与计量单位\n" +
                "2.11.1 物理量的名称和符号\n" +
                "物理量的名称和符号应符合GB3100～3102-86的规定。论文中某一量的名称和符号应统一。\n" +
                "物理量的符号必须采用斜体。表示物理量的符号作下标时也用斜体。\n" +
                "2.11.2 物理量计量单位\n" +
                "物理量计量单位及符号应按国务院1984年发布的《中华人民共和国法定计量单位》及GB3100～3102执行，不得使用非法定计量单位及符号。计量单位可采用汉字或符号，但应前后统一。计量单位符号，除用人名命名的单位第一个字母用大写之外，一律用小写字母。\n" +
                "非物理量单位（如件、台、人、元、次等）可以采用汉字与单位符号混写的方式，如“万t·km”，“t/(人·a)”等。\n" +
                "文稿叙述中不定数字之后允许用中文计量单位符号，如“几千克至1000kg”。\n" +
                "表达时刻时应采用中文计量单位，如“上午8点3刻”，不能写成“8h45min”，应写成“8：45”。\n" +
                "计量单位符号一律用正体。\n" +
                "2.12 外文字母的正、斜体用法\n" +
                "按照GB3100～3102-86及GB7159-87的规定使用，即物理量符号、物理常量、变量符号用斜体，计量单位等符号均用正体。\n" +
                "2.13 数字\n" +
                "按国家语言文字工作委员会等七单位1987年发布的《关于出版物上数字用法的试行规定》，除习惯用中文数字表示的以外，一般均采用阿拉伯数字(参照附录4)，年份一律写全数，如2010年，不能写成10年。\n" +
                "2.14 公式\n" +
                "公式原则上应居中书写。若公式前有文字（如“解”、“假定”等），文字空两格写，公式仍居中写。公式末不加标点。公式较长时，最好在“=”前转行，如难实现，可在“+、-、×、÷”运算符号处转行，运算符号应在转行后的行首，公式的编号用“（）”括起来放在公式右边行末。\n" +
                "公式序号按章编排，如第1章第一个公式序号为“(1-1)”，附录2中的第一个公式为【2-1】等。\n" +
                "文中引用公式时，一般用“见式(1-1)”或“由公式(1-1)”。\n" +
                "公式中用斜线表示“除”的关系时应采用括号，以免含糊不清，如。通常“乘”的关系在前，如而不能写成。\n" +
                "2.15 插表\n" +
                "表格不加左、右边线，即采用三线表，在一张页面上。\n" +
                "每个表格均应有表题（由表序和表名组成）。表序一般按章编排，如第1章第一个插表的序号为“表1-1”等。表序与表名之间空一格，表名中不允许使用标点符号，表名后不加标点。表题置于表上，用中、英文两种文字居中排写，中文在上，要求用黑体5号字居中，英文新罗马五号居中。\n" +
                "表头设计应简单明了，尽量不用斜线。表头中可采用化学符号或物理量符号。\n" +
                "全表如用同一单位，则将单位符号移至表头右上角，加圆括号。\n" +
                "表中数据应准确无误，书写清楚。数字空缺的格内加横线“－”（占2个数字宽度）。表内文字或数字上、下或左、右相同时，采用通栏处理方式，不允许用“〃”、“同上”之类的写法。\n" +
                "表内文字说明（5号宋体），起行空一格、转行顶格、句末不加标点。\n" +
                "表中若有附注用小5号宋体，写在表的下方，句末加标点。如有一条附注，写成“注：” ；若有多条附注，写成“注1：注2:……”.\n" +
                "2.16 插图\n" +
                "插图应与文字紧密配合，文图相符，内容正确。选图要力求精练。对无规定符号的图形应采用该行业的常用画法。\n" +
                "2.16.1 图题及图中说明\n" +
                "每个图均应有图题（由图号和图名组成）。图号按章编排，如第1章第一个插图的图号为“图1-1”等。图题置于图下，用中、英文两种文字居中书写，中文在上，要求用宋体5号字。有图注或其它说明时应置于图题之上。图名在图号之后空一格排写。引用图应注明出处，在图题右上角加引用文献号。图中若有分图时，分图题置于分图之下，分图号用a)、b)等表示。\n" +
                "图中各部分说明应采用中文（引用的外文图除外）或数字项号，各项文字说明置于图题之上（有分图题者，置于分图题之上）。\n" +
                "2.16.2 插图编排\n" +
                "插图之前，文中必须有关于本插图的提示，如“见图1-1”、“如图1-1所示”等。插图与其图题为一个整体，不得拆开排写于两页。插图处的该页空白不够排写该图整体时，则可将其后文字部分提前排写，将图移到次页最前面。\n" +
                "2.16.3 坐标单位\n" +
                "    有数字标注的坐标图，必须注明坐标单位。\n" +
                "2.16.4 论文原件中照片图及插图\n" +
                "学位论文原件中的照片图均应是原版照片粘贴，不得采用复印方式。照片可为黑白或彩色，应主题突出、层次分明、清晰整洁、反差适中。照片采用光面相纸，不宜用布纹相纸。对金相显微组织照片必须注明放大倍数。\n" +
                "学位论文原件中的插图不得采用复印件。对于复杂的引用图，可采用数字化仪表输入计算机打印出来的图稿。\n" +
                "2.17 参考文献\n" +
                "2.17.1 参考文献编写项目和顺序规定\n" +
                "a．专著、论文集、学位论文、报告\n" +
                "[序号] 主要责任者．文献题名[文献类型标识]．出版地：出版者，出版年．起止页码．\n" +
                "b．期刊文章\n" +
                "[序号] 主要责任者．文献题名[文献类型标识]．刊名，年，卷（期）：起止页码．\n" +
                "c．论文集中的析出文献\n" +
                "[序号］析出文献主要责任者．析出文献题名[文献类型标识]．原文献主要责任者（任选）．原文献题名[文献类型标识]．出版地：出版者，出版年．析出文献起止页码．\n" +
                "d．报纸文章\n" +
                "[序号] 主要责任者．文献题名[文献类型标识]．报纸名，出版日期（版次）．\n" +
                "e．国际、国家标准\n" +
                "[序号] 标准编号，标准名称[文献类型标识]．\n" +
                "f．专利\n" +
                "[序号] 专利所有者．专利题名[文献类型标识]．专利国别：专利号，出版日期．\n" +
                "g．电子文献\n" +
                "[序号] 主要责任者．电子文献题名[电子文献及载体类型标识]．电子文献的出处或可获得地址，发表或更新日期／引用日期（任选）．\n" +
                "h．各种未定义类型的文献";
        float maxLineHeight = getDisplayMetrics().heightPixels - 20;
        int lineBeginIndex = 0, lineEndIndex = 0;
        //每一个字的宽度
        float[] oneWordWidth = new float[str.length()];
        Log.e(TAG, str.charAt(startWord)+"qe" );
        float lastLineHeight = 0;
        //下一行要显示的起始字符索引。
        //字总宽度
        float totalWordWidth = 0;
        StringBuffer lineStr = new StringBuffer();
        for (int i = startWord; i < str.length(); i++) {
            //测量每一个字的宽度
            oneWordWidth[i] = mPaint.measureText(str, i, i + 1);

            //字宽度累加
            totalWordWidth += oneWordWidth[i];

            //如果超过了行宽
            if (totalWordWidth > lineWidth) {

                //获取超过当前行宽的索引
                lineEndIndex = i;
                //计算实际显示的行宽(减去最后超出的字的宽度)
                totalWordWidth -= oneWordWidth[i];
                //获取当前实际显示的字符串
                lineStr.setLength(0);
                lineStr.append(str.substring(lineBeginIndex, lineEndIndex));
                Log.e(TAG, str.substring(lineBeginIndex, lineEndIndex));
                //如果当前行字串宽度不占满行宽，计算当前行每个字符需要均分的宽度，即字间距。
                float averageWidth = (lineWidth - totalWordWidth) / (lineEndIndex - lineBeginIndex);
                //下一行要显示的起始字符索引。
                int j = lineBeginIndex;
                lineBeginIndex = lineEndIndex;
                float sumWidth = 0;
                //由于不满一行字款，需要把每个字符加上字间距一个个画出来，
                StringBuffer ones = new StringBuffer();
                lastLineHeight = lastLineHeight + lineHeight;
                while (j < lineEndIndex) {
                    //画出每个字符
                    canvas.drawText(String.valueOf(str.charAt(j)), sumWidth, lastLineHeight, mPaint);
                    //计算每一个字在x轴上的偏移量
                    sumWidth += oneWordWidth[j] + averageWidth;
                    j++;
                }
                lastLineHeight = lastLineHeight + lineSpace;
                //由于下一行最后一个字符的字宽被舍去，重置当前累加行宽为下一行第一个字符的字宽
                totalWordWidth = oneWordWidth[i];
                Log.e(TAG, lastLineHeight + "");
                //i恰好等于最后一个字符，需要再循环一次。
//                if (i == str.length() - 1) {
//                    i = str.length() - 2;
//                }
            } else {
                if (i == str.length() - 1) {
                    //获取总字串的最后一行字符串。
                    lineStr.setLength(0);
                    lineStr.append(str.substring(lineBeginIndex, str.length()));
                    //Log.d(TAG,"lastLineH ="+lastLineHeight+"linestr ="+lineStr.toString());
                    lastLineHeight = lastLineHeight + lineHeight;
                    canvas.drawText(lineStr.toString(), 0, lastLineHeight, mPaint);
                    Log.e(TAG, str.substring(lineBeginIndex, str.length()));
                }
            }

            //如果最后一行文字大于屏幕高度
            if (lastLineHeight + mPaint.measureText(str, lineEndIndex, lineEndIndex + 1) > maxLineHeight - getStatusBarHeight()) {
                nextFirstIndex = lineEndIndex;
                break;
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(event.getX() > 800){
                    startWord = nextFirstIndex;

                    postInvalidate();
                }
                break;
        }
        return true;
    }

    private int getStatusBarHeight() {

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getContext().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
