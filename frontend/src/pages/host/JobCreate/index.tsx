import { nanoid } from 'nanoid';

import SectionCard from '@/components/host/SectionCard';

import useSections from '@/hooks/useSections';

import styles from './styles';

const DATA = {
  name: '청소',
  sections: [
    {
      name: '대강의실',
      tasks: [{ name: '책상 닦기' }, { name: '칠판 닦기' }],
    },
    {
      name: '소강의실',
      tasks: [{ name: '책상 닦기' }, { name: '칠판 닦기' }],
    },
  ],
};

const JobCreate: React.FC = () => {
  const { sections, createSection, editSection, deleteSection, createTask, editTask, deleteTask } = useSections();

  return (
    <div css={styles.layout}>
      <div css={styles.contents}>
        <p css={styles.pageTitle}>{DATA.name}</p>
        <div css={styles.grid}>
          {sections.map((section, sectionIndex) => (
            <SectionCard
              section={section}
              sectionIndex={sectionIndex}
              createTask={createTask}
              editSection={editSection}
              editTask={editTask}
              deleteTask={deleteTask}
              deleteSection={deleteSection}
              key={nanoid()}
            />
          ))}
          <div css={styles.createCard} onClick={createSection}>
            +
          </div>
        </div>
      </div>
    </div>
  );
};
export default JobCreate;
