package plus.cove.infrastructure.mask;

/**
 * 覆盖格式化
 * <p>
 * 根据特定格式进行格式化
 * 包括生成，解析和格式化
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class MaskFormatter {
    private final static char MASKER_BEGIN = '{';
    private final static char MASKER_END = '}';

    private final static char MASKER_INDEX = '-';
    private final static char MASKER_RAW = ':';

    /**
     * 生成
     *
     * @param source 原始字符
     * @param begin  开始位置
     * @param length 截取长度
     * @return 生成后的格式
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String generate(String source, int begin, int length) {
        return String.format("%cm%d-%d:%s%c", MASKER_BEGIN, begin, begin + length, source, MASKER_END);
    }

    /**
     * 解析
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static String resolve(String target, String mask) {
        StringBuilder builder = new StringBuilder(target.length());

        for (int i = 0; i < target.length(); i++) {
            char word = target.charAt(i);

            // mask
            MaskReader reader = new MaskReader();
            if (word == MASKER_BEGIN) {
                i++;
                char tar = target.charAt(i);

                // 格式内容
                reader.setMasker(String.valueOf(tar));

                // 开始索引
                i++;
                String beginIndex = "";
                do {
                    tar = target.charAt(i);
                    beginIndex = beginIndex.concat(String.valueOf(tar));
                    i++;
                }
                while (target.charAt(i) != MASKER_INDEX);
                reader.setBeginIndex(Integer.valueOf(beginIndex));

                // 结束索引
                i++;
                String endIndex = "";
                do {
                    tar = target.charAt(i);
                    endIndex = endIndex.concat(String.valueOf(tar));
                    i++;
                }
                while (target.charAt(i) != MASKER_RAW);
                reader.setEndIndex(Integer.valueOf(endIndex));

                // mask结束
                i++;
                String raw = "";
                do {
                    tar = target.charAt(i);
                    raw = raw.concat(String.valueOf(tar));
                    i++;
                }
                while (target.charAt(i) != MASKER_END);
                reader.setRawData(raw);

                // 格式化
                String masked = reader.format(mask);
                builder.append(masked);
                continue;
            }

            // 非mask
            word = target.charAt(i);
            builder.append(word);
        }

        return builder.toString();
    }
}
