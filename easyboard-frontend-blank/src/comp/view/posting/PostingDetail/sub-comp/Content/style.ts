
import { createStyles, Theme, WithStyles as MuiWithStyles, withStyles as muiWithStyles } from '@material-ui/core/styles';


const style = (theme: Theme) => createStyles({
  media: {
    textAlign: 'center',
    margin: theme.spacing(2),
    height: 500,
  },
  content: {
    paddingTop: 0,
  },
  subContent: {
    marginBottom: 0,
    paddingBottom: theme.spacing(1),
  },
});

export default style;
export type WithStyles = MuiWithStyles<typeof style>;
export const withStyles = muiWithStyles(style);
