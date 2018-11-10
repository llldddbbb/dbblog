/**
 *  映射tag颜色
 */
export function mapTagColor (index) {
  switch (index % 4) {
    case 0:
      return 'blue'
    case 1:
      return 'green'
    case 2:
      return 'red'
    case 3:
      return 'yellow'
  }
}
