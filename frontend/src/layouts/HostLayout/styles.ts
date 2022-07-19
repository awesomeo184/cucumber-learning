import { css } from '@emotion/react';

import theme from '@/styles/theme';

const layout = (isManagePath: boolean) => css`
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  background-color: ${theme.colors.background};
  padding-left: ${isManagePath ? '224px' : '0'};
`;

const styles = { layout };

export default styles;
