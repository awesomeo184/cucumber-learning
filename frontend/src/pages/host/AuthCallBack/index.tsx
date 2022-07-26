import { useEffect } from 'react';
import { useQuery } from 'react-query';
import { useNavigate } from 'react-router-dom';

import useGitHubLogin from '@/hooks/useGithubLogin';

import apis from '@/apis';

import styles from './styles';

const AuthCallBack: React.FC = () => {
  const navigate = useNavigate();

  const { isSuccessGithubLogin } = useGitHubLogin();

  const { data, refetch: getSpaceData } = useQuery(['spaces'], apis.getSpaces, {
    suspense: true,
    enabled: false,
  });

  useEffect(() => {
    if (isSuccessGithubLogin) {
      getSpaceData();
    }
  }, [isSuccessGithubLogin]);

  useEffect(() => {
    if (data) {
      if (data.spaces.length === 0) {
        navigate('/host/manage/spaceCreate');
        return;
      }

      const space = data.spaces[0];
      navigate(`/host/manage/${space.id}`);
    }
  }, [data]);

  return (
    <div css={styles.layout}>
      <div>로그인 진행 중...</div>
    </div>
  );
};

export default AuthCallBack;
